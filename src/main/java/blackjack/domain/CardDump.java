package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDump {
    List<Card> cardDump;

    public CardDump() {
        cardDump = initializeCardDump();
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cardDump);
    }

    private List<Card> initializeCardDump() {
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
            throw new IllegalStateException("[ERROR] 카드 덤프가 비어 있습니다!");
        }
        return cardDump.removeFirst();
    }
}
