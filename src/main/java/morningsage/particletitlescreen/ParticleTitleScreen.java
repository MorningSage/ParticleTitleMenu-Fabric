package morningsage.particletitlescreen;

import morningsage.particletitlescreen.config.ConfigFileHandler;
import morningsage.particletitlescreen.config.ModConfig;
import morningsage.particletitlescreen.events.TitleScreenCreatedEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class ParticleTitleScreen implements ClientModInitializer {
	public static final String MOD_ID = "particletitlescreen";
	public static final ConfigFileHandler configFileHandler = new ConfigFileHandler(ModConfig.class, MOD_ID);
	public static ParticleScreenManager particleScreenManager;

	@Override
	public void onInitializeClient() {
		TitleScreenCreatedEvent.ON_CREATED.register(() -> particleScreenManager = new ParticleScreenManager());
	}
}
