package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class AiryAmerican extends Cheese {
    public AiryAmerican() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.JUMP, 1200, 0), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case SKY:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
            case BUILDING:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case LAND:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case NETHER:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
        }
        return super.getAged(ageEnvironment);
    }
}
