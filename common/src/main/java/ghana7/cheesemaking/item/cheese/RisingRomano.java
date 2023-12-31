package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RisingRomano extends Cheese{
    public RisingRomano() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .setAlwaysEdible(),
                3);
    }

    //adapted from ChorusFruitItem
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (!worldIn.isRemote) {
            double d0 = entityLiving.getPosX();
            double d1 = entityLiving.getPosY();
            double d2 = entityLiving.getPosZ();

            double d4 = worldIn.func_234938_ad_() - 1;//MathHelper.clamp(entityLiving.getPosY() + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.func_234938_ad_() - 1));

            if (entityLiving.isPassenger()) {
                entityLiving.stopRiding();
            }

            if (entityLiving.attemptTeleport(d0, d4, d2, true)) {
                SoundEvent soundevent = entityLiving instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                worldIn.playSound((PlayerEntity)null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entityLiving.playSound(soundevent, 1.0F, 1.0F);
            }
        }

        return itemstack;
    }

    @Override
    public Item getAged(Cheese.EnvironmentType ageEnvironment) {
        return CheesemakingMod.RISING_ROMANO.get();
    }
}
