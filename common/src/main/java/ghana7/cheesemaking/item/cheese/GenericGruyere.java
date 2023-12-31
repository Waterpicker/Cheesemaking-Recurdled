package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Item;

public class GenericGruyere extends Cheese {
    public GenericGruyere() {
        super(4, 4, 1);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.POWERFUL_PARMESAN.get();
            case SKY:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case BUILDING:
                return CheesemakingMod.HEALTHY_HAVARTI.get();
            case LAND:
                return CheesemakingMod.HEALTHY_HAVARTI.get();
            case NETHER:
                return CheesemakingMod.GENERIC_GRUYERE.get();
        }
        return super.getAged(ageEnvironment);
    }
}
