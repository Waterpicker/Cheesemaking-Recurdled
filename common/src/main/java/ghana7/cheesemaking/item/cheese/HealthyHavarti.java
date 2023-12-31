package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HealthyHavarti extends Cheese {
    public HealthyHavarti() {
        super(new Food.Builder()
        .hunger(6)
        .saturation(10)
        .effect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 3), 1),
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
