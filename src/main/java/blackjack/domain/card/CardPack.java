package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardPack {

    private final List<Card> cards;

    public CardPack(BlackjackShuffle blackjackShuffle) {
        this.cards = initCards();
        blackjackShuffle.shuffle(cards);
    }

    public List<Card> getDealByCount(final int count) {
        return IntStream.range(0, count)
                .mapToObj(count1 -> cards.removeLast())
                .toList();
    }

    public List<Card> getCards() {
        return cards;
    }

    private List<Card> initCards() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .collect(Collectors.toList());
    }
}
