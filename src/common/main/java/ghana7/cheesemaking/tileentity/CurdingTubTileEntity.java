package ghana7.cheesemaking.tileentity;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.block.ItemStackHandler;
import ghana7.cheesemaking.item.Cheese;
import ghana7.cheesemaking.item.Rennet;
import ghana7.cheesemaking.item.cheese.Curd;
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
    public ItemStackHandler itemHandler = createHandler();
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                if(slot == 0) {
                    return stack.getItem() instanceof Rennet;
                }
                if(slot == 1) {
                    return stack.getItem() instanceof Curd;
                }
                return true;
            }

            @Override
            protected int getStackLimit(int slot, ItemStack stack) {
                return 4;
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
                return super.extractItem(slot, amount, simulate);
            }
        };
    }

    public NonNullList<ItemStack> getAllItems() {
        NonNullList<ItemStack> items = NonNullList.create();
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            items.add(stack);
        }
        return items;
    }

    private int timerMax = 60; //3 seconds
    private int timer = timerMax;
    private int milkCapacity = 4000;
    private int currentMilkAmount = 0;

    public CurdingTubTileEntity(BlockPos blockPos, BlockState blockState) {
        super(CheesemakingMod.CURDING_TUB_TE.get(), blockPos, blockState);
        timer = timerMax;
    }

    public void tick() {
        if(level.isClientSide) {
            return;
        }

        if(timer > 0) {
            if(currentMilkAmount >= 1000 && !itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(1).getCount() < 4) {
                System.out.println("Tub: " + timer);
                timer--;
            }
        }

        if(timer <= 0) {
            System.out.println("A curd has arrived!");
            timer = timerMax;
            removeMilk(1000);
            itemHandler.extractItem(0, 1, false);
            itemHandler.insertItem(1, new ItemStack(CheesemakingMod.CURD.get(), 1), false);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        timer = tag.getInt("timer");
        currentMilkAmount = tag.getInt("milkAmount");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.putInt("timer", timer);
        tag.putInt("milkAmount", currentMilkAmount);
        super.saveAdditional(tag);
    }
    @Override
    public @NotNull CompoundTag getUpdateTag() {
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
        System.out.println("Milk: " + amount);
        if(currentMilkAmount >= amount) {
            currentMilkAmount -= amount;

            System.out.println("Milk: " + amount +  " :D");
            //CheesemakingMod.LOGGER.debug(currentMilkAmount);
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
            return true;
        }

        System.out.println("Milk: " + amount +  " :(");
        return false;
    }

    public int getMilk() {
        return currentMilkAmount;
    }

}