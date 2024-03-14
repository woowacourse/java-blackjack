package domain.game.deck;

import domain.constants.Outcome;
import domain.participant.Player;
import java.util.function.Function;

public interface PlayerOutcomeFunction extends Function<Player, Outcome> {
}
