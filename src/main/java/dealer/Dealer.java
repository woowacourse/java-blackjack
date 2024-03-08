package dealer;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipant;
import java.util.ArrayList;

public class Dealer extends GameParticipant {

    private static final int MIN_DEALER_SCORE = 16;
    private static final int INIT_CARD_SIZE = 2;

    private final CardDeck cardDeck;

    public Dealer() {
        super(new ArrayList<>());
        this.cardDeck = new CardDeck();
        initDealerCard();
    }

    private void initDealerCard() {
        this.receiveCard(giveCard());
    }

    public void checkNeedExtraCard() {
        receiveCard(giveCard());

        while (getMaxGameScore() <= MIN_DEALER_SCORE) {
            receiveCard(giveCard());
        }
    }

    public int countExtraCard() {
        return getCardsSize() - INIT_CARD_SIZE;
    }

    public Card giveCard() {
        cardDeck.shuffleCard();
        return cardDeck.pickCard();
    }
}
