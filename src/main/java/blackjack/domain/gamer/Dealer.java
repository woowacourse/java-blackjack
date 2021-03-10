package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    private static final int MAX_AVAILABLE_TO_GET_CARD = 16;

    public Dealer(List<Card> cards){
        super(new Name("딜러"), cards);
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

    private boolean lose(int playerResult, int dealerResult) {
        if (playerResult <= Cards.BLACK_JACK && dealerResult > Cards.BLACK_JACK) {
            return true;
        }
        if (playerResult <= Cards.BLACK_JACK && playerResult > dealerResult) {
            return true;
        }
        return false;
    }

    private boolean win(int playerResult, int dealerResult) {
        if (playerResult > Cards.BLACK_JACK && dealerResult <= Cards.BLACK_JACK) {
            return true;
        }
        if (dealerResult <= Cards.BLACK_JACK && playerResult < dealerResult) {
            return true;
        }
        return false;
    }
}
