package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class BulkyBleu extends Cheese {
    public BulkyBleu() {
        super(new FoodProperties.Builder()
                        .nutrition(10)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 1), 1)
                        .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), 1),
                3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.BULKY_BLEU.get();
    }
}
