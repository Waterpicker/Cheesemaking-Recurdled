package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RisingRomano extends Cheese{
    public RisingRomano() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(10)
                        .alwaysEat(),
                3);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        super.onUseTick(level, livingEntity, itemStack, i);
        if (!level.isClientSide()) {
            double d0 = livingEntity.getX();
            double d1 = livingEntity.getY();
            double d2 = livingEntity.getZ();

            double d4 = level.getHeight() - 1;//MathHelper.clamp(entityLiving.getPosY() + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.func_234938_ad_() - 1));

            if (livingEntity.isPassenger()) {
                livingEntity.stopRiding();
            }

            if (livingEntity.randomTeleport(d0, d4, d2, true)) {
                SoundEvent soundevent = livingEntity instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                level.playSound((Player) null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                livingEntity.playSound(soundevent, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public Item getAged(Cheese.EnvironmentType ageEnvironment) {
        return CheesemakingMod.RISING_ROMANO.get();
    }
}
