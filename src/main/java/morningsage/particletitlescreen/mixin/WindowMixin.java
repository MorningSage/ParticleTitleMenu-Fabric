package morningsage.particletitlescreen.mixin;

import morningsage.particletitlescreen.events.MouseEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
@Environment(EnvType.CLIENT)
public class WindowMixin {
    @Inject(
        at = @At("RETURN"),
        method = "onCursorEnterChanged"
    )
    private void onCursorEnterChanged(long window, boolean entered, CallbackInfo callbackInfo) {
        if (entered) {
            MouseEvents.ON_ENTER.invoker().onEnter();
        } else {
            MouseEvents.ON_LEAVE.invoker().onLeave();
        }
    }
}
