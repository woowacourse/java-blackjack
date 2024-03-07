package dealer;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipant;
import java.util.ArrayList;

public class Dealer extends GameParticipant {

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

        while (getMaxGameScore() <= 16) {
            receiveCard(giveCard());
        }
    }

    public int countExtraCard() {
        return getCardsSize() - 2;
    }

    public Card giveCard() {
        cardDeck.shuffleCard();
        return cardDeck.pickCard();
    }
}
