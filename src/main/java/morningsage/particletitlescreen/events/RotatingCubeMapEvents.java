package morningsage.particletitlescreen.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public class RotatingCubeMapEvents {

    public static final Event<RotatingCubeMapRendering> ON_RENDER = EventFactory.createArrayBacked(RotatingCubeMapRendering.class,
        callbacks -> () -> {

        for (RotatingCubeMapRendering event : callbacks) {
            ActionResult result = event.onRendering();

            if (result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    @FunctionalInterface
    public interface RotatingCubeMapRendering {
        ActionResult onRendering();
    }
}
