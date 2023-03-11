package domain;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;

public class Judge {

    public static GameResult of(Dealer dealer, Player player) {
        int dealerScore = dealer.getCards().getScore();
        int playerScore = player.getCards().getScore();
        return getResult(dealerScore, playerScore);
    }

    private static GameResult getResult(int dealerScore, int playerScore) {
        if (playerScore > Cards.BLACKJACK_NUMBER) {
            return GameResult.LOSE;
        }
        if (dealerScore > Cards.BLACKJACK_NUMBER || playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
