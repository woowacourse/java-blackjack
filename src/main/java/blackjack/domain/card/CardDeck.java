package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>(CardFactory.values());
        shuffleCards();
    }

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    private void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card pick() {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("모든 카드가 소진되어 게임을 종료합니다.");
        }
    }

    public List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(pick());
        pick.add(pick());
        return pick;
    }
}
