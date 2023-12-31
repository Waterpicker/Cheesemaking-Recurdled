package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class LuckyLimberger extends Cheese {
    public LuckyLimberger() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.LUCK, 1200, 1), 1),
                2);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.MINERS_MOZZARELLA.get();
            case SKY:
                return CheesemakingMod.LUCKY_LIMBURGER.get();
            case BUILDING:
                return CheesemakingMod.LUCKY_LIMBURGER.get();
            case LAND:
                return CheesemakingMod.LUCKY_LIMBURGER.get();
            case NETHER:
                return CheesemakingMod.LUCKY_LIMBURGER.get();
        }
        return super.getAged(ageEnvironment);
    }
}
