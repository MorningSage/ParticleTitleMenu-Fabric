package morningsage.particletitlescreen.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class TitleScreenCreatedEvent {
    public static final Event<TitleScreenCreated> ON_CREATED = EventFactory.createArrayBacked(TitleScreenCreated.class,
        callbacks -> () -> {

        for (TitleScreenCreated event : callbacks) {
            event.onCreated();
        }
    });

    @FunctionalInterface
    public interface TitleScreenCreated {
        void onCreated();
    }
}
