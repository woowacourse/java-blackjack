package blackjack.domain;

import blackjack.domain.strategy.CardGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    public final Queue<Card> cardDeck = new LinkedList<>();

    public CardDeck(CardGenerator cardGenerator) {
        List<Card> cards = cardGenerator.generate();
        cardDeck.addAll(cards);
    }

    public Card drawCard() {
        return pollOneCard();
    }

    public List<Card> drawTwoCards() {
        return List.of(pollOneCard(), pollOneCard());
    }

    private Card pollOneCard() {
        if (cardDeck.isEmpty()) {
            throw new NullPointerException("[ERROR] 카드를 모두 소진했습니다.");
        }
        return cardDeck.poll();
    }

}
