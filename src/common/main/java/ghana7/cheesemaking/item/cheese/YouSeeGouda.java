package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class YouSeeGouda extends Cheese {
    public YouSeeGouda() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod( 10)
                        .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 0), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.MINERS_MOZZARELLA.get();
            case SKY:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case BUILDING:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case LAND:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case NETHER:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
        }
        return super.getAged(ageEnvironment);
    }
}
