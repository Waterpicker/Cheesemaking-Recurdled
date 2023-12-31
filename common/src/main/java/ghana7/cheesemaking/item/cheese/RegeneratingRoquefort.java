package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class RegeneratingRoquefort extends Cheese {
    public RegeneratingRoquefort() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.REGENERATION, 300, 1), 1),
                2);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.BULKY_BLEU.get();
            case SKY:
                return CheesemakingMod.REGENERATING_ROQUEFORT.get();
            case BUILDING:
                return CheesemakingMod.GIGANTIC_GORGONZOLA.get();
            case LAND:
                return CheesemakingMod.BULKY_BLEU.get();
            case NETHER:
                return CheesemakingMod.REGENERATING_ROQUEFORT.get();
        }
        return super.getAged(ageEnvironment);
    }
}
