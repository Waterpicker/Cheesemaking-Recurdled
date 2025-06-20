package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class RapidRicotta extends Cheese {
    public RapidRicotta() {
        super(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1),
                2);
    }

    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        switch (ageEnvironment) {
            case CAVE:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case SKY:
                return CheesemakingMod.RISING_ROMANO.get();
            case BUILDING:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case LAND:
                return CheesemakingMod.RAPID_RICOTTA.get();
            case NETHER:
                return CheesemakingMod.RAPID_RICOTTA.get();
        }
        return super.getAged(ageEnvironment);
    }
}
