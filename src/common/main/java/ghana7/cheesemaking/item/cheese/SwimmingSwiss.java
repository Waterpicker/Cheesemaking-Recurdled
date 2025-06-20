package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SwimmingSwiss extends Cheese {
    public SwimmingSwiss() {
        super(new FoodProperties.Builder()
                .nutrition(6)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 3000, 0), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.SWIMMING_SWISS.get();
    }
}
