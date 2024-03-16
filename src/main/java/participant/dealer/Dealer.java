package participant.dealer;

import card.Card;
import card.CardDeck;
import java.util.List;
import participant.Participant;

public class Dealer extends Participant {

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
