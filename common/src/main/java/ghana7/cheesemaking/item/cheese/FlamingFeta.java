package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FlamingFeta extends Cheese {

    public FlamingFeta() {
        super(new Food.Builder()
                .hunger(6)
                .saturation(10)
                .effect(new EffectInstance(Effects.FIRE_RESISTANCE, 3000, 0), 1),
                2);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.FLAMING_FETA.get();
            case SKY:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
            case BUILDING:
                return CheesemakingMod.FLAMING_FETA.get();
            case LAND:
                return CheesemakingMod.FLAMING_FETA.get();
            case NETHER:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
        }
        return super.getAged(ageEnvironment);
    }
}
