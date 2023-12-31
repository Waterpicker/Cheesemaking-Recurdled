package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class YouSeeGouda extends Cheese {
    public YouSeeGouda() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.NIGHT_VISION, 2400, 0), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.MINERS_MOZZARELLA.get();
            case SKY:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case BUILDING:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case LAND:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
            case NETHER:
                return CheesemakingMod.YOU_SEE_GOUDA.get();
        }
        return super.getAged(ageEnvironment);
    }
}
