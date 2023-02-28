package domain.card;

import util.PickerStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final String ERROR_EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다";

    private static final List<Card> cards = new ArrayList<>();

    static {
        cards.addAll(Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList()));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card pollAvailableCard(PickerStrategy picker) {
        if (cards.size() == 0) {
            throw new IllegalStateException(ERROR_EMPTY_CARDS);
        }
        int next = picker.next(cards.size());

        return cards.remove(next);
    }
}
