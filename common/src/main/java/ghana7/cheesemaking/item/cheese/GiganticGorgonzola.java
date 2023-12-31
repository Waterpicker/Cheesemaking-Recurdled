package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GiganticGorgonzola extends Cheese {
    public GiganticGorgonzola() {
        super(14,14,3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.GIGANTIC_GORGONZOLA.get();
    }
}
