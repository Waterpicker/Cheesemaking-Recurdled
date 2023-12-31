package ghana7.cheesemaking.tileentity;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CurdingTubTileEntity extends BlockEntity {
    public SimpleContainer itemHandler = createHandler();
    private SimpleContainer createHandler() {
        return new SimpleContainer(2) {
            @Override
            public void setChanged() {
                CurdingTubTileEntity.this.setChanged();
            }

            @Override
            public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
                return stack.getItem() instanceof Cheese;
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public void setItem(int slot, @NotNull ItemStack stack) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
                super.setItem(slot, stack);
            }

            @Override
            public ItemStack removeItem(int slot, int amount) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
                return super.removeItem(slot, amount);
            }
        };
    }

    public NonNullList<ItemStack> getAllItems() {
        return IntStream.range(0, itemHandler.getContainerSize()).mapToObj(a -> itemHandler.getItem(a)).collect(Collectors.toCollection(NonNullList::create));
    }

    private final int timerMax = 60; //3 seconds
    private int timer = timerMax;
    private final int milkCapacity = 4000;
    private int currentMilkAmount = 0;

    public CurdingTubTileEntity(BlockPos blockPos, BlockState blockState) {
        super(CheesemakingMod.CURDING_TUB_TE.get(), blockPos, blockState);
        timer = timerMax;
    }

    public void tick() {
        if(level.isClientSide()) {
            return;
        }

        if(timer > 0) {
            if(currentMilkAmount >= 1000 && !itemHandler.getItem(0).isEmpty() && itemHandler.getItem(1).getCount() < 4) {
                timer--;
            }
        }

        if(timer <= 0) {
            timer = timerMax;
            removeMilk(1000);
            itemHandler.removeItem(0, 1);
            itemHandler.setItem(1, new ItemStack(CheesemakingMod.CURD.get(), 1));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.fromTag(tag.getList("inv", Tag.TAG_COMPOUND));
        timer = tag.getInt("timer");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.createTag());
        tag.putInt("timer", timer);
        super.saveAdditional(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        var nbt = new CompoundTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // the number here is generally ignored for non-vanilla TileEntities, 0 is safest
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public boolean addMilk(int amount) {
        if(currentMilkAmount + amount <= milkCapacity) {
            currentMilkAmount += amount;
            //CheesemakingMod.LOGGER.debug(currentMilkAmount);
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            return true;
        }
        return false;
    }

    public boolean removeMilk(int amount) {
        if(currentMilkAmount >= amount) {
            currentMilkAmount -= amount;
            //CheesemakingMod.LOGGER.debug(currentMilkAmount);
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            return true;
        }
        return false;
    }

    public int getMilk() {
        return currentMilkAmount;
    }

}
