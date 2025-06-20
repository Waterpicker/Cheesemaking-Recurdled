package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.item.Item;

public class BoringBrie extends Cheese {
    public BoringBrie() {
        super(3, 3, 1);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.LUCKY_LIMBURGER.get();
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
