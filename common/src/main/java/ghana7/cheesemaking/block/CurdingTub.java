package ghana7.cheesemaking.block;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Rennet;
import ghana7.cheesemaking.item.cheese.Curd;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CurdingTub extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public CurdingTub() {
        super(Properties.copy(Blocks.OAK_WOOD)
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

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            //CheesemakingMod.LOGGER.debug(handIn);
            ItemStack stack = player.getItemInHand(interactionHand);
            CurdingTubTileEntity curdingTubTileEntity = (CurdingTubTileEntity) level.getBlockEntity(blockPos);
            if(stack.getItem() instanceof MilkBucketItem) {
                if(curdingTubTileEntity.addMilk(1000)) {
                    if(!player.isCreative()) {
                        player.setItemInHand(interactionHand, new ItemStack(Items.BUCKET, 1));
                    }
                }
                return InteractionResult.SUCCESS;
            } else if(stack.getItem() instanceof BucketItem) {
                if(curdingTubTileEntity.removeMilk(1000)) {
                    if(!player.isCreative()) {
                        player.setItemInHand(interactionHand, new ItemStack(Items.MILK_BUCKET, 1));
                    }
                }
                return InteractionResult.SUCCESS;
            } else if(stack.getItem() instanceof Rennet) {
                ItemStack newStack = curdingTubTileEntity.itemHandler.addItem(stack);
                if(!player.isCreative()) {
                    player.setItemInHand(interactionHand, newStack);
                }

                return InteractionResult.SUCCESS;
            } else if(!curdingTubTileEntity.itemHandler.getItem(1).isEmpty() && (stack.isEmpty() || stack.getItem() instanceof Curd)) {
                if(stack.isEmpty()) {
                    player.setItemInHand(interactionHand, curdingTubTileEntity.itemHandler.getItem(1));
                } else {
                    player.getItemInHand(interactionHand).grow(curdingTubTileEntity.itemHandler.getItem(1).getCount());
                }
                return InteractionResult.SUCCESS;
            } else if(!curdingTubTileEntity.itemHandler.getItem(0).isEmpty() && (stack.isEmpty() || stack.getItem() instanceof Rennet)) {
                if(stack.isEmpty()) {
                    player.setItemInHand(interactionHand, curdingTubTileEntity.itemHandler.getItem(0));
                } else {
                    player.getItemInHand(interactionHand).grow(curdingTubTileEntity.itemHandler.getItem(0).getCount());
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, blockPos, blockState, player);
        if(level.getBlockEntity(blockPos) instanceof CurdingTubTileEntity tileEntity) {
            Containers.dropContents(level, blockPos, tileEntity.getAllItems());
        }
    }
}
