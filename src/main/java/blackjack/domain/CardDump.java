package blackjack.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDump implements CardProvider {
    static final String EMPTY_CARD_DUMP = "[ERROR] 카드 덤프가 비어 있습니다!";

    private final Queue<Card> cardDump = initializeCards();

    public CardDump() {
        shuffleCards();
    }

    @Override
    public Queue<Card> initializeCards() {
        Queue<Card> cards = new ArrayDeque<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    @Override
    public void shuffleCards() {
        List<Card> cards = new ArrayList<>(cardDump);
        Collections.shuffle(cards);

        cardDump.clear();
        cardDump.addAll(cards);
    }

    @Override
    public Card drawCard() {
        if (cardDump.isEmpty()) {
            throw new IllegalStateException(EMPTY_CARD_DUMP);
        }
        return cardDump.poll();
    }
}
