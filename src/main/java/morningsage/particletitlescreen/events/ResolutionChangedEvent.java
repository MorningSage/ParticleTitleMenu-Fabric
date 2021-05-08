package morningsage.particletitlescreen.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class ResolutionChangedEvent {
    public static final Event<ResolutionChanged> ON_CHANGED = EventFactory.createArrayBacked(ResolutionChanged.class,
        callbacks -> () -> {

        for (ResolutionChanged event : callbacks) {
            event.onChanged();
        }
    });

    @FunctionalInterface
    public interface ResolutionChanged {
        void onChanged();
    }
}
