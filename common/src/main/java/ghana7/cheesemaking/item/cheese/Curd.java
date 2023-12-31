package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.item.Item;

public class Curd extends Cheese {
    public Curd() {
        super(1, 1, 0);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.SATURATING_STILTON.get();
            case SKY:
                return CheesemakingMod.GENERIC_GRUYERE.get();
            case BUILDING:
                return CheesemakingMod.GENERIC_GRUYERE.get();
            case LAND:
                return CheesemakingMod.GENERIC_GRUYERE.get();
            case NETHER:
                return CheesemakingMod.SNACK_JACK.get();
        }
        return super.getAged(ageEnvironment);
    }
}
