package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.item.Item;


public class GiganticGorgonzola extends Cheese {
    public GiganticGorgonzola() {
        super(14,14,3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.GIGANTIC_GORGONZOLA.get();
    }
}
