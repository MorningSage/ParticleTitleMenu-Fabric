package morningsage.particletitlescreen.mixin;

import morningsage.particletitlescreen.events.TitleScreenCreatedEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
@Environment(EnvType.CLIENT)
public class TitleScreenMixin {
    @Inject(
        at = @At("RETURN"),
        method = "<init>(Z)V"
    )
    public void init(boolean doBackgroundFade, CallbackInfo callbackInfo) {
        TitleScreenCreatedEvent.ON_CREATED.invoker().onCreated();
    }
}
