package ghana7.cheesemaking.item;

import ghana7.cheesemaking.CheesemakingMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public abstract class Cheese extends Item {
    public enum EnvironmentType {CAVE, SKY, BUILDING, LAND, NETHER}
    private final int tier;
    public Cheese(int hunger, float saturation, int tier) {
        super(new Item.Properties().food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build()).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS));
        this.tier = tier;
    }

    public Cheese(FoodProperties.Builder builder, int tier) {
        super(new Item.Properties().food(builder.build()).arch$tab(CreativeModeTabs.FOOD_AND_DRINKS));
        this.tier = tier;
    }

    public Item getAged(EnvironmentType ageEnvironment) {
        return CheesemakingMod.FLAWED_CHEESE.get();
    }
}
