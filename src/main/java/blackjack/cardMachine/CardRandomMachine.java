package blackjack.cardMachine;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardRandomMachine implements CardMachine {

    private List<Card> deck;
    private final Map<Card, Boolean> cardUsage;

    public CardRandomMachine() {
        cardUsage = new HashMap<>();
    }

    @Override
    public void receiveDeck(final List<Card> deck) {
        this.deck = deck;
        for (Card card : deck) {
            cardUsage.put(card, false);
        }
    }

    @Override
    public Card drawOneCard() {
        final ArrayList<Card> mutableDeck = new ArrayList<>(deck);
        Collections.shuffle(mutableDeck);
        final Card drawCard = mutableDeck.getFirst();
        if (isAlreadyUsed(drawCard)) {
            return drawOneCard();
        }
        cardUsage.replace(drawCard, true);
        return drawCard;
    }

    private Boolean isAlreadyUsed(final Card drawCard) {
        return cardUsage.get(drawCard);
    }
}
