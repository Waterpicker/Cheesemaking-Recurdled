package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class RapidRicotta extends Cheese {
    public RapidRicotta() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.SPEED, 1200, 1), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case SKY:
                return CheesemakingMod.RISING_ROMANO.get();
            case BUILDING:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case LAND:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case NETHER:
                return CheesemakingMod.RAPID_RICOTTA.get();
        }
        return super.getAged(ageEnvironment);
    }
}
