package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Item;

public class SaturatingStilton extends Cheese {
    public SaturatingStilton() {
        super(3, 12.0f, 1);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.POWERFUL_PARMESAN.get();
            case SKY:
                return CheesemakingMod.REGENERATING_ROQUEFORT.get();
            case BUILDING:
                return CheesemakingMod.REGENERATING_ROQUEFORT.get();
            case LAND:
                return CheesemakingMod.REGENERATING_ROQUEFORT.get();
            case NETHER:
                return CheesemakingMod.SATURATING_STILTON.get();
        }
        return super.getAged(ageEnvironment);
    }
}
