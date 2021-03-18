package morningsage.particletitlescreen.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import morningsage.particletitlescreen.events.RotatingCubeMapEvents;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RotatingCubeMapRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class RotatingCubeMapRendererMixin {
	@Inject(
		at = @At("HEAD"),
		method = "render",
		cancellable = true
	)
	public void render(float delta, float alpha, CallbackInfo info) {
		ActionResult result = RotatingCubeMapEvents.ON_RENDER.invoker().onRendering();

		if (result != ActionResult.PASS) info.cancel();
	}
}
