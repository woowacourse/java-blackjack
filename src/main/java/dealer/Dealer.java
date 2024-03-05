package dealer;

import card.Card;
import card.CardDeck;
import java.util.List;
import random.RandomGenerator;

public class Dealer {

    private static final int MIN_DECK_SIZE = 0;

    private final RandomGenerator randomGenerator;
    private final CardDeck cardDeck;

    private List<Card> cards;

    public Dealer(RandomGenerator randomGenerator, CardDeck cardDeck) {
        this.randomGenerator = randomGenerator;
        this.cardDeck = cardDeck;
    }

    public Card giveCard() {
        int randomIndex = randomGenerator.getRandomNumberInRange(MIN_DECK_SIZE, cardDeck.getTotalCardSize());

        return cardDeck.pickCard(randomIndex);
    }
}
