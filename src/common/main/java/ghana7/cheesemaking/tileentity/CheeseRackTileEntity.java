package ghana7.cheesemaking.tileentity;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.block.ItemStackHandler;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheeseRackTileEntity extends BlockEntity {
    public ItemStackHandler itemHandler = createHandler();
    private ItemStackHandler  createHandler() {
        return new ItemStackHandler(8) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return stack.getItem() instanceof Cheese;
            }

            @Override
            protected int getStackLimit(int slot, ItemStack stack) {
                return 1;
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

    private final int timerMax = 1200; //60 seconds
    private int timer = timerMax;

    public CheeseRackTileEntity(BlockPos pos, BlockState state) {
        super(CheesemakingMod.CHEESE_RACK_TE.get(), pos, state);
        timer = timerMax;
    }

    public void tick() {
        if(level.isClientSide()) {
            return;
        }

        if(timer > 0) {
            System.out.println("Rack " + timer);
            timer--;
        }

        if(timer <= 0) {
            System.out.println("ITS CHEESE TIME!");
            timer = timerMax;
            //CheesemakingMod.LOGGER.debug(getEnvironmentType());
            Cheese.EnvironmentType envType = getEnvironmentType();
            for(int i = 0; i < 8; i++) {
                if(itemHandler.getStackInSlot(i).getItem() instanceof Cheese) {
                    Cheese cheeseItem = (Cheese)(itemHandler.getStackInSlot(i).getItem());
                    ItemStack newCheese = new ItemStack(cheeseItem.getAged(envType), 1);
                    itemHandler.extractItem(i, 1, false);
                    itemHandler.insertItem(i, newCheese, false);
                }
            }
        }
    }

    private Cheese.EnvironmentType getEnvironmentType() {
        assert level != null;
        if(level.dimension().equals(Level.NETHER)) {
            return Cheese.EnvironmentType.NETHER;
        }
        if(level.canSeeSky(getBlockPos())) {
            if(getBlockPos().getY() > 120) {
                return Cheese.EnvironmentType.SKY;
            } else {
                return Cheese.EnvironmentType.LAND;
            }
        } else {
            if(getBlockPos().getY() < 60) {
                return Cheese.EnvironmentType.CAVE;
            } else {
                return Cheese.EnvironmentType.BUILDING;
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        timer = tag.getInt("timer");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.putInt("timer", timer);
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
}
