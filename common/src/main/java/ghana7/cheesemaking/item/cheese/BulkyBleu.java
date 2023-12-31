package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BulkyBleu extends Cheese {
    public BulkyBleu() {
        super(new Food.Builder()
                        .hunger(10)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.RESISTANCE, 1200, 1), 1)
                        .effect(new EffectInstance(Effects.ABSORPTION, 1200, 1), 1),
                3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.BULKY_BLEU.get();
    }
}
