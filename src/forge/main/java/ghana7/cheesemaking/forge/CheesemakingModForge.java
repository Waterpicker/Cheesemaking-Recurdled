package ghana7.cheesemaking.forge;

import dev.architectury.platform.forge.EventBuses;
import ghana7.cheesemaking.CheesemakingMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CheesemakingMod.MODID)
public class CheesemakingModForge {
    public CheesemakingModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CheesemakingMod.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        CheesemakingMod.init();
    }

}
