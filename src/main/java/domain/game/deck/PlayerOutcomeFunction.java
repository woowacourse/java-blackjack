package domain.game.deck;

import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.function.BiFunction;

public interface PlayerOutcomeFunction extends BiFunction<Player, Dealer, Outcome> {
}
