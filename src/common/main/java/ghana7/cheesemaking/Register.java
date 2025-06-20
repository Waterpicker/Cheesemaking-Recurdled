package ghana7.cheesemaking;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface Register<T> {
    Holder<T> register(String name, Supplier<T> supplier);
}

@FunctionalInterface
interface RegisterFactory {
    public <T> Register<T> create(ResourceKey<Registry<T>> key, Registry<T> registry);
}
