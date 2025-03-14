package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
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

    // TODO: size 제한하기
    public List<Card> takeCards(int size) {
        validateEmpty(size);

        return IntStream.range(0, size)
                .mapToObj(i -> cards.removeLast())
                .toList();
    }

    private void validateEmpty(int size) {
        if (cards.size() < size) {
            throw new IllegalArgumentException("모든 카드를 소진하였습니다.");
        }
    }
}
