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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheeseRackTileEntity extends BlockEntity {
    public SimpleContainer itemHandler = createHandler();
    private SimpleContainer createHandler() {
        return new SimpleContainer(8) {
            @Override
            public void setChanged() {
                CheeseRackTileEntity.this.setChanged();
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
            timer--;
        }

        if(timer <= 0) {
            timer = timerMax;
            //CheesemakingMod.LOGGER.debug(getEnvironmentType());
            Cheese.EnvironmentType envType = getEnvironmentType();
            for(int i = 0; i < 8; i++) {
                if(itemHandler.getItem(i).getItem() instanceof Cheese cheeseItem) {
                    ItemStack newCheese = new ItemStack(cheeseItem.getAged(envType), 1);
                    itemHandler.removeItem(i, 1);
                    itemHandler.setItem(i, newCheese);
                }
            }
        }
    }

    private Cheese.EnvironmentType getEnvironmentType() {
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
}
