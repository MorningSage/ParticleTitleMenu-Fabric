package morningsage.particletitlescreen.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class MouseEvents {

    public static final Event<MouseMoving> ON_MOVE = EventFactory.createArrayBacked(MouseMoving.class,
        callbacks -> (long window, double x, double y) -> {

        for (MouseMoving event : callbacks) {
            event.onMove(window, x, y);
        }
    });

    public static final Event<MouseEntering> ON_ENTER = EventFactory.createArrayBacked(MouseEntering.class,
        callbacks -> () -> {

        for (MouseEntering event : callbacks) {
            event.onEnter();
        }
    });

    public static final Event<MouseLeaving> ON_LEAVE = EventFactory.createArrayBacked(MouseLeaving.class,
        callbacks -> () -> {

        for (MouseLeaving event : callbacks) {
            event.onLeave();
        }
    });

    @FunctionalInterface
    public interface MouseMoving {
        void onMove(long window, double x, double y);
    }
    @FunctionalInterface
    public interface MouseEntering {
        void onEnter();
    }
    @FunctionalInterface
    public interface MouseLeaving {
        void onLeave();
    }
}
