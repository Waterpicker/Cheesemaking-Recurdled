package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class LuckyLimberger extends Cheese {
    public LuckyLimberger() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.LUCK, 1200, 1), 1),
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
