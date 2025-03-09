package domain.rule;

import domain.GameResult;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Player;

public interface GameRule {
    boolean isBurst(Hand hand);

    boolean isBlackJack(Hand hand);

    int getScore(Hand hand);

    GameResult getResult(Player player, Dealer dealer);
}
