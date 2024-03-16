package dealer;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipantCards;
import java.util.List;

public class Dealer extends GameParticipantCards {

    private static final int MIN_DEALER_SCORE = 16;

    public Dealer(List<Card> initCards) {
        super(initCards);
    }

    public Card getFirstCard() {
        return getCards().getFirstCard();
    }

    public void playGame(CardDeck cardDeck) {
        while (isNotOverMinScore()) {
            receiveCard(cardDeck.pickCard());
        }
    }

    private boolean isNotOverMinScore() {
        return getCardScore() <= MIN_DEALER_SCORE;
    }
}
