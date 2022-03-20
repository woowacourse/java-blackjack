package blackjack.domain.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class CardDeck {

    private final Queue<Card> cards;

    private CardDeck(final Queue<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck createNewShuffledCardDeck() {
        return new CardDeck(createNewShuffledCards());
    }

    private static Queue<Card> createNewShuffledCards() {
        return new ArrayDeque<>(Randoms.shuffle(createNewCards()));
    }

    private static List<Card> createNewCards() {
        return Arrays.stream(Suit.values())
                .map(CardDeck::createSuitCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createSuitCards(final Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination))
                .collect(Collectors.toList());
    }

    public Card provideCard() {
        checkCardDeckEmpty();
        return cards.poll();
    }

    private void checkCardDeckEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드덱이 비어 카드를 제공할 수 없습니다.");
        }
    }
}
