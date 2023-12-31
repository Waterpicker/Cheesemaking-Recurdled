package ghana7.cheesemaking;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import ghana7.cheesemaking.block.CheeseRack;
import ghana7.cheesemaking.block.CurdingTub;
import ghana7.cheesemaking.block.MilkBlock;
import ghana7.cheesemaking.item.Rennet;
import ghana7.cheesemaking.item.cheese.*;
import ghana7.cheesemaking.rendering.CheeseRackTileEntityRenderer;
import ghana7.cheesemaking.rendering.CurdingTubTileEntityRenderer;
import ghana7.cheesemaking.tileentity.CheeseRackTileEntity;
import ghana7.cheesemaking.tileentity.CurdingTubTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CheesemakingMod.MODID)
public class CheesemakingMod {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "cheesemaking";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Registries.BLOCK);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Registries.ITEM);
    private static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(MODID, Registries.BLOCK_ENTITY_TYPE);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(MODID, Registries.MENU);

    //blocks
    public static final RegistrySupplier<Block> CURDING_TUB = BLOCKS.register("curding_tub", () ->
            new CurdingTub()
    );

    public static final RegistrySupplier<Block> MILK_BLOCK = BLOCKS.register("milk_block", () -> new Block(BlockBehaviour.Properties.of()));

    public static final RegistrySupplier<Block> CHEESE_RACK = BLOCKS.register("cheese_rack", CheeseRack::new
    );
    //blockitems
    public static final RegistrySupplier<Item> CURDING_TUB_BI = ITEMS.register("curding_tub", () -> new BlockItem(CURDING_TUB.get(), new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));

    public static final RegistrySupplier<Item> MILK_BLOCK_BI = ITEMS.register("milk_block", () -> new BlockItem(MILK_BLOCK.get(), new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));

    public static final RegistrySupplier<BlockItem> CHEESE_RACK_BI = ITEMS.register("cheese_rack", () -> new BlockItem(CHEESE_RACK.get(), new Item.Properties().arch$tab(CreativeModeTabs.BUILDING_BLOCKS)));

    //items
    public static final RegistrySupplier<Item> RENNET = ITEMS.register("rennet", () -> new Rennet());

    //t0 cheeses
    public static final RegistrySupplier<Item> CURD = ITEMS.register("curd", () -> new Curd());

    public static final RegistrySupplier<Item> FLAWED_CHEESE = ITEMS.register("flawed_cheese", () -> new FlawedCheese());

    //t1 cheeses
    public static final RegistrySupplier<Item> BORING_BRIE = ITEMS.register("boring_brie", () -> new BoringBrie());

    public static final RegistrySupplier<Item> GENERIC_GRUYERE = ITEMS.register("generic_gruyere", () -> new GenericGruyere());

    public static final RegistrySupplier<Item> SATURATING_STILTON = ITEMS.register("saturating_stilton", () -> new SaturatingStilton());

    public static final RegistrySupplier<Item> SNACK_JACK = ITEMS.register("snack_jack", () -> new SnackJack());

    //t2 cheeses
    public static final RegistrySupplier<Item> AIRY_AMERICAN = ITEMS.register("airy_american", () -> new AiryAmerican());

    public static final RegistrySupplier<Item> FLAMING_FETA = ITEMS.register("flaming_feta", () -> new FlamingFeta());

    public static final RegistrySupplier<Item> HEALTHY_HAVARTI = ITEMS.register("healthy_havarti", () -> new HealthyHavarti());

    public static final RegistrySupplier<Item> LUCKY_LIMBURGER = ITEMS.register("lucky_limburger", () -> new LuckyLimberger());

    public static final RegistrySupplier<Item> POWERFUL_PARMESAN = ITEMS.register("powerful_parmesan", () -> new PowerfulParmesan());

    public static final RegistrySupplier<Item> RAPID_RICOTTA = ITEMS.register("rapid_ricotta", () -> new RapidRicotta());

    public static final RegistrySupplier<Item> REGENERATING_ROQUEFORT = ITEMS.register("regenerating_roquefort", () -> new RegeneratingRoquefort());

    public static final RegistrySupplier<Item> RISING_ROMANO = ITEMS.register("rising_romano", () -> new RisingRomano());

    public static final RegistrySupplier<Item> SWIMMING_SWISS = ITEMS.register("swimming_swiss", () -> new SwimmingSwiss());

    public static final RegistrySupplier<Item> YOU_SEE_GOUDA = ITEMS.register("you_see_gouda", () -> new YouSeeGouda());

    //t3 cheeses

    public static final RegistrySupplier<Item> BULKY_BLEU = ITEMS.register("bulky_bleu", () -> new BulkyBleu());

    public static final RegistrySupplier<Item> CHAOTIC_CHEDDAR = ITEMS.register("chaotic_cheddar", () -> new ChaoticCheddar());

    public static final RegistrySupplier<Item> GIGANTIC_GORGONZOLA = ITEMS.register("gigantic_gorgonzola", () -> new AiryAmerican());

    public static final RegistrySupplier<Item> MINERS_MOZZARELLA = ITEMS.register("miners_mozzarella", () -> new MinersMozzarella());




    //tile entities
    public static final RegistrySupplier<BlockEntityType<CurdingTubTileEntity>> CURDING_TUB_TE = TILE_ENTITY_TYPES.register(
            "curding_tub", () -> BlockEntityType.Builder.of(CurdingTubTileEntity::new, CURDING_TUB.get()).build(null)
    );

    public static final RegistrySupplier<BlockEntityType<CheeseRackTileEntity>> CHEESE_RACK_TE = TILE_ENTITY_TYPES.register(
            "cheese_rack", () -> BlockEntityType.Builder.of(CheeseRackTileEntity::new, CHEESE_RACK.get()).build(null)
    );


    public CheesemakingMod() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


    }

    private void doClientStuff(final FMLClientSetupEvent event) {


        // do something that can only be done on the client
        BlockEntityRendererRegistry.register(CURDING_TUB_TE.get(), CurdingTubTileEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(CHEESE_RACK_TE.get(), CheeseRackTileEntityRenderer::new);

        RenderTypeLookup.setRenderLayer(CURDING_TUB.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(CHEESE_RACK.get(), RenderType.getTranslucent());
    }


}
