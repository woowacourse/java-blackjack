package blackjack.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDump {
    static final String EMPTY_CARD_DUMP = "[ERROR] 카드 덤프가 비어 있습니다!";
    static final String CARD_AMOUNT_LIMIT_EXCEEDED = "[ERROR] 카드를 더 뽑을 수 없습니다!";

    private static final int CARD_AMOUNT_LIMIT = 2;
    private final Queue<Card> cardDump = initializeCardDump();

    public CardDump() {
        shuffleCardDump();
    }

    private void shuffleCardDump() {
        List<Card> cards = new ArrayList<>(cardDump);
        Collections.shuffle(cards);

        cardDump.clear();
        cardDump.addAll(cards);
    }

    private Queue<Card> initializeCardDump() {
        Queue<Card> cards = new ArrayDeque<>();
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
        return cardDump.poll();
    }

    public List<Card> drawCards() {
        List<Card> cards = new ArrayList<>();
        if (cardDump.size() < CARD_AMOUNT_LIMIT) {
            throw new IllegalStateException(CARD_AMOUNT_LIMIT_EXCEEDED);
        }
        cards.add(cardDump.poll());
        cards.add(cardDump.poll());
        return cards;
    }
}
