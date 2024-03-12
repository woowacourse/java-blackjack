package domain.game.deck;

import domain.participant.Player;
import java.util.function.Predicate;

public interface PlayerWinningCondition extends Predicate<Player> {
}
