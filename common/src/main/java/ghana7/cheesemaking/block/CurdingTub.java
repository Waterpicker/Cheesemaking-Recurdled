package ghana7.cheesemaking.block;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Rennet;
import ghana7.cheesemaking.item.cheese.Curd;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import ghana7.cheesemaking.tileentity.CurdingTubTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CurdingTub extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public CurdingTub() {
        super(Properties.copy(Blocks.OAK_PLANKS)
                .sound(SoundType.WOOD)
                .strength(2.0F)
                .noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return CheesemakingMod.CURDING_TUB_TE.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide && blockEntityType == CheesemakingMod.CURDING_TUB_TE.get() ? (level1, blockPos, blockState1, blockEntity) -> ((CurdingTubTileEntity) blockEntity).tick() : null;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {

        if(worldIn.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            //CheesemakingMod.LOGGER.debug(handIn);
            ItemStack stack = player.getItemInHand(handIn);
            CurdingTubTileEntity curdingTubTileEntity = (CurdingTubTileEntity) worldIn.getBlockEntity(pos);
            if(stack.getItem() instanceof MilkBucketItem) {
                if(curdingTubTileEntity.addMilk(1000)) {
                    if(!player.isCreative()) {
                        player.setItemInHand(handIn, new ItemStack(Items.BUCKET, 1));
                    }
                }
                return InteractionResult.SUCCESS;
            } else if(stack.getItem() instanceof BucketItem) {
                if(curdingTubTileEntity.removeMilk(1000)) {
                    if(!player.isCreative()) {
                        player.setItemInHand(handIn, new ItemStack(Items.MILK_BUCKET, 1));
                    }
                }
                return InteractionResult.SUCCESS;
            } else if(stack.getItem() instanceof Rennet) {
                ItemStack newStack = curdingTubTileEntity.itemHandler.insertItem(0, stack, false);
                if(!player.isCreative()) {
                    player.setItemInHand(handIn, newStack);
                }

                return InteractionResult.SUCCESS;
            } else if(!curdingTubTileEntity.itemHandler.getStackInSlot(1).isEmpty() && (stack.isEmpty() || stack.getItem() instanceof Curd)) {
                if(stack.isEmpty()) {
                    player.setItemInHand(handIn, curdingTubTileEntity.itemHandler.extractItem(1, 4, false));
                } else {
                    player.getItemInHand(handIn).grow(curdingTubTileEntity.itemHandler.extractItem(1, 4, false).getCount());
                }
                return InteractionResult.SUCCESS;
            } else if(!curdingTubTileEntity.itemHandler.getStackInSlot(0).isEmpty() && (stack.isEmpty() || stack.getItem() instanceof Rennet)) {
                if(stack.isEmpty()) {
                    player.setItemInHand(handIn, curdingTubTileEntity.itemHandler.extractItem(0, 4, false));
                } else {
                    player.getItemInHand(handIn).grow(curdingTubTileEntity.itemHandler.extractItem(0, 4, false).getCount());
                }
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, blockPos, blockState, player);

        BlockEntity tileEntity = level.getBlockEntity(blockPos);
        if(tileEntity instanceof CurdingTubTileEntity) {
            Containers.dropContents(level, blockPos, ((CurdingTubTileEntity)tileEntity).getAllItems());
        }
    }
}