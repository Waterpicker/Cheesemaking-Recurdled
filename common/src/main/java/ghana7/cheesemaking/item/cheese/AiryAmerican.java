package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class AiryAmerican extends Cheese {
    public AiryAmerican() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.JUMP_BOOST, 1200, 0), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case SKY:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
            case BUILDING:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case LAND:
                return CheesemakingMod.AIRY_AMERICAN.get();
            case NETHER:
                return CheesemakingMod.CHAOTIC_CHEDDAR.get();
        }
        return super.getAged(ageEnvironment);
    }
}
