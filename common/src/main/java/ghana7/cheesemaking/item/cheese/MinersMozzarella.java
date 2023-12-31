package ghana7.cheesemaking.item.cheese;

import ghana7.cheesemaking.CheesemakingMod;
import ghana7.cheesemaking.item.Cheese;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MinersMozzarella extends Cheese {
    public MinersMozzarella() {
        super(new Food.Builder()
                        .hunger(6)
                        .saturation(10)
                        .effect(new EffectInstance(Effects.HASTE, 3000, 0), 1)
                        .effect(new EffectInstance(Effects.NIGHT_VISION, 3000, 0), 1),
                3);
    }
    @Override
    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.MINERS_MOZZARELLA.get();
    }
}
