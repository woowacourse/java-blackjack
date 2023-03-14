package blackjack.domain.participant.comparator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Player;

public interface Comparator {
    void setNext();

    WinTieLose compareWithPlayer(Player player);
}
