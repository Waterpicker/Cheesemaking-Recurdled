package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class HealthyHavarti extends Cheese {
    public HealthyHavarti() {
        super(new FoodProperties.Builder()
        .nutrition(6)
        .saturationMod(10)
        .effect(new MobEffectInstance(MobEffects.HEAL, 1, 3), 1),
         2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.BULKY_BLEU.get();
            case SKY:
                return CheesemakingMod.HEALTHY_HAVARTI.get();
            case BUILDING:
                return CheesemakingMod.GIGANTIC_GORGONZOLA.get();
            case LAND:
                return CheesemakingMod.BULKY_BLEU.get();
            case NETHER:
                return CheesemakingMod.HEALTHY_HAVARTI.get();
        }
        return super.getAged(ageEnvironment);
    }
}
