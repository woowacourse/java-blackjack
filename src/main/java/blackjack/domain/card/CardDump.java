package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDump {
    private static final String EMPTY_CARD_DUMP = "[ERROR] 카드 덤프가 비어 있습니다!";

    private final List<Card> cardDump;

    private CardDump(List<Card> cardDump) {
        this.cardDump = cardDump;
    }

    public static CardDump shuffledDump() {
        List<Card> cards = initializeCardDump();
        Collections.shuffle(cards);
        return new CardDump(cards);
    }

    private static List<Card> initializeCardDump() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    public Card drawCard() {
        if (cardDump.isEmpty()) {
            throw new IllegalStateException(EMPTY_CARD_DUMP);
        }
        return cardDump.removeFirst();
    }
}
