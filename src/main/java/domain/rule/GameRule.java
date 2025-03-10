package domain.rule;

import domain.GameResult;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;

public interface GameRule {
    boolean isBurst(Cards cards);

    boolean isWin(Cards cards);

    int getScore(Cards cards);

    GameResult getResult(Player player, Dealer dealer);
}
