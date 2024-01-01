package ghana7.cheesemaking.block;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import ghana7.cheesemaking.tileentity.CurdingTubTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class CheeseRack extends Block implements EntityBlock {
    public CheeseRack() {
        super(Properties.copy(Blocks.OAK_PLANKS)
                .sound(SoundType.WOOD)
                .strength(2.0F)
                .noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return CheesemakingMod.CHEESE_RACK_TE.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide && blockEntityType == CheesemakingMod.CHEESE_RACK_TE.get() ? (level1, blockPos, blockState1, blockEntity) -> {
            System.out.println("EEP!");
            ((CheeseRackTileEntity) blockEntity).tick();
        } : null;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if(worldIn.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            //CheesemakingMod.LOGGER.debug(handIn);
            ItemStack stack = player.getItemInHand(handIn);
            CheeseRackTileEntity cheeseRackTileEntity = (CheeseRackTileEntity) worldIn.getBlockEntity(pos);
            Vec3 hitPos = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
            //CheesemakingMod.LOGGER.debug(hitPos);
            int hitIndex = 0;
            if(hitPos.x > 0.5) {
                hitIndex += 1;
            }
            if(hitPos.y > 0.5) {
                hitIndex += 4;
            }
            if(hitPos.z > 0.5) {
                hitIndex += 2;
            }
            //CheesemakingMod.LOGGER.debug(hitIndex);
            if(cheeseRackTileEntity.itemHandler.getStackInSlot(hitIndex).isEmpty() && stack.getItem() instanceof Cheese) {
                ItemStack newStack = cheeseRackTileEntity.itemHandler.insertItem(hitIndex, stack, false);
                if(!player.isCreative()) {

                    player.setItemInHand(handIn, newStack);
                }
                return InteractionResult.SUCCESS;
            } else if(!cheeseRackTileEntity.itemHandler.getStackInSlot(hitIndex).isEmpty() && (stack.isEmpty() || stack.getItem().equals(cheeseRackTileEntity.itemHandler.getStackInSlot(hitIndex).getItem()))) {

                if(stack.isEmpty()) {
                    player.setItemInHand(handIn, cheeseRackTileEntity.itemHandler.extractItem(hitIndex, 1, false));
                } else {
                    player.getItemInHand(handIn).grow(cheeseRackTileEntity.itemHandler.extractItem(hitIndex, 1, false).getCount());
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
        if(tileEntity instanceof CheeseRackTileEntity) {
            Containers.dropContents(level, blockPos, ((CheeseRackTileEntity)tileEntity).getAllItems());
        }
    }
}