package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class MinersMozzarella extends Cheese {
    public MinersMozzarella() {
        super(new FoodProperties.Builder()
                .nutrition(6)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0), 1)
                        .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3000, 0), 1),
                3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.MINERS_MOZZARELLA.get();
    }
}
