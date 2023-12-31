package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SnackJack extends Cheese {
    public SnackJack() {
        super(new FoodProperties.Builder()
        .nutrition(3)
        .saturationMod(3)
        .fast(), 1);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case SKY:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case BUILDING:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case LAND:
                return CheesemakingMod.SWIMMING_SWISS.get();
            case NETHER:
                return CheesemakingMod.FLAMING_FETA.get();
        }
        return super.getAged(ageEnvironment);
    }
}
