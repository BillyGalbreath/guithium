package net.pl3x.guithium.api.action.actions.player.screen;

import java.util.ArrayList;
import java.util.List;
import net.pl3x.guithium.api.action.RegisteredHandler;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.player.WrappedPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Action that fires when a screen is opened.
 */
public class ScreenOpenedAction extends ScreenAction {
    private static final List<RegisteredHandler> handlers = new ArrayList<>();

    /**
     * Create a new action for when a screen is opened.
     *
     * @param player Player that performed the action
     * @param screen Screen action was performed on
     */
    public ScreenOpenedAction(@NotNull WrappedPlayer player, @NotNull Screen screen) {
        super(player, screen);
    }

    @Override
    @NotNull
    public List<RegisteredHandler> getHandlers() {
        return handlers;
    }
}
