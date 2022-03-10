package blackjack.domain;

import blackjack.domain.strategy.CardGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class CardDeck {
    public static final Queue<Card> cardDeck = new LinkedList<>();

    public CardDeck(CardGenerator cardGenerator) {
        List<Card> cards = cardGenerator.generate();
        cardDeck.addAll(cards);
    }

    public Card drawCard() {
        return cardDeck.poll();
    }

    public List<Card> drawTwoCards() {
        return List.of(Objects.requireNonNull(cardDeck.poll()), Objects.requireNonNull(cardDeck.poll()));
    }

}
