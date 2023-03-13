package domain;

import domain.card.Cards;
import domain.gamestate.GameState;
import domain.gamestate.GameStates;
import domain.participant.Dealer;
import domain.participant.Player;

public class Judge {

    public static GameState gameResult(Dealer dealer, Player player) {
        int dealerScore = dealer.getCards().getScore();
        int playerScore = player.getCards().getScore();
        boolean isBlackJack = player.getCards().isBlackJack();
        return getGameState(dealerScore, playerScore, isBlackJack);
    }

    private static GameState getGameState(int dealerScore, int playerScore, boolean isBlackJack) {
        if (playerScore > Cards.BLACKJACK_NUMBER) {
            return GameStates.LOSE;
        }
        if (dealerScore > Cards.BLACKJACK_NUMBER || playerScore > dealerScore) {
            if (isBlackJack) {
                return GameStates.BLACKJACK;
            }
            return GameStates.WIN;
        }
        if (dealerScore > playerScore) {
            return GameStates.LOSE;
        }
        return GameStates.DRAW;
    }
}
