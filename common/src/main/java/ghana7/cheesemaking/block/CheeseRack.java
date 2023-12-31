package ghana7.cheesemaking.block;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import ghana7.cheesemaking.tileentity.CurdingTubTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CheeseRack extends Block implements EntityBlock {
    public CheeseRack() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                .sound(SoundType.WOOD)
                .strength(2.0F)
                .noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return CheesemakingMod.CHEESE_RACK_TE.get().create();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            //CheesemakingMod.LOGGER.debug(handIn);
            ItemStack stack = player.getHeldItem(handIn);
            CheeseRackTileEntity cheeseRackTileEntity = (CheeseRackTileEntity) worldIn.getTileEntity(pos);
            Vector3d hitPos = hit.getHitVec().subtract(pos.getX(), pos.getY(), pos.getZ());
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

                    player.setHeldItem(handIn, newStack);
                }
                return ActionResultType.SUCCESS;
            } else if(!cheeseRackTileEntity.itemHandler.getStackInSlot(hitIndex).isEmpty() && (stack.isEmpty() || stack.getItem().equals(cheeseRackTileEntity.itemHandler.getStackInSlot(hitIndex).getItem()))) {

                if(stack.isEmpty()) {
                    player.setHeldItem(handIn, cheeseRackTileEntity.itemHandler.extractItem(hitIndex, 1, false));
                } else {
                    player.getHeldItem(handIn).grow(cheeseRackTileEntity.itemHandler.extractItem(hitIndex, 1, false).getCount());
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        super.playerWillDestroy(level, blockPos, blockState, player);
        if(level.getBlockEntity(blockPos) instanceof CheeseRackTileEntity tileEntity) {
            Containers.dropContents(level, blockPos, tileEntity.getAllItems());
        }
    }
}
