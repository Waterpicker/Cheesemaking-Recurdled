package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChaoticCheddar extends Cheese {
    public ChaoticCheddar() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(10)
                        .alwaysEat(),
                3);
    }

    @Override
    public void onUseTick(Level worldIn, LivingEntity entityLiving, ItemStack itemstack, int a) {
        super.onUseTick(worldIn, entityLiving, itemstack, a);
        if (!worldIn.isClientSide) {
            double d0 = entityLiving.getX();
            double d1 = entityLiving.getY();
            double d2 = entityLiving.getZ();

            for(int i = 0; i < 16; ++i) {
                double d3 = entityLiving.getX() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                double d4 = Mth.clamp(entityLiving.getY() + (double)(entityLiving.getRandom().nextInt(16) - 8), 0.0D, (double)(worldIn.getHeight() - 1));
                double d5 = entityLiving.getZ() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (entityLiving.isPassenger()) {
                    entityLiving.stopRiding();
                }

                if (entityLiving.randomTeleport(d3, d4, d5, true)) {
                    SoundEvent soundevent = entityLiving instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    worldIn.playSound(null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(soundevent, 1.0F, 1.0F);
                    break;
                }
            }

            if (entityLiving instanceof Player) {
                ((Player)entityLiving).getCooldowns().addCooldown(this, 20);
            }
        }
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.CHAOTIC_CHEDDAR.get();
    }
}
