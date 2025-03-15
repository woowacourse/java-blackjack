package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Deck {

    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    public Deck() {
        List<Card> cards = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
        validate(cards);
        this.cards = new ArrayList<>(shuffled(cards));
    }

    private void validate(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException("카드 개수는 52개여야 합니다.");
        }

        if (new HashSet<>(cards).size() != DECK_SIZE) {
            throw new IllegalArgumentException("중복된 카드가 존재합니다.");
        }

    }

    private List<Card> shuffled(List<Card> cards) {
        List<Card> copiedCards = new ArrayList<>(cards);
        Collections.shuffle(copiedCards);

        return copiedCards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> takeStartingCards() {
        return List.of(cards.removeLast(), cards.removeLast());
    }

    public Card takeSingleCard() {
        validateEmpty();
        return cards.removeLast();
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("모든 카드를 소진하였습니다.");
        }
    }
}
