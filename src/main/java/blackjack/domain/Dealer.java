package blackjack.domain;

import java.util.List;

public class Dealer extends Playable {
    private static final int MAX_AVAILABLE_TO_GET_CARD = 16;

    public Dealer(List<Card> cards){
        super("딜러", cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return super.sumCards() <= MAX_AVAILABLE_TO_GET_CARD;
    }

    public Outcome resultOfPlayer(int playerResult) {
        int dealerResult = sumCardsForResult();

        if (win(playerResult, dealerResult)) {
            return Outcome.LOSE;
        }

        if (lose(playerResult, dealerResult)) {
            return Outcome.WIN;
        }

        return Outcome.DRAW;
    }

    private boolean lose(int counterpart, int playerSum) {
        if (counterpart <= Cards.BLACK_JACK && playerSum > Cards.BLACK_JACK) {
            return true;
        }
        if (counterpart <= Cards.BLACK_JACK && counterpart > playerSum) {
            return true;
        }
        return false;
    }

    private boolean win(int counterpart, int playerSum) {
        if (counterpart > Cards.BLACK_JACK && playerSum <= Cards.BLACK_JACK) {
            return true;
        }
        if (playerSum <= Cards.BLACK_JACK && counterpart < playerSum) {
            return true;
        }
        return false;
    }
}
