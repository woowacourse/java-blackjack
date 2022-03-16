package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final String NO_AVAILABLE_CARD_EXCEPTION = "[ERROR] 덱이 비었습니다.";

    private final List<Card> cards;
    private final List<Boolean> isExist;

    private Deck(List<Card> cards) {
        this.cards = cards;
        this.isExist = new ArrayList<>(Collections.nCopies(cards.size(), true));
    }

    public static Deck makeBlackjackDeck() {
        List<Card> cards = Arrays.stream(BlackjackCardType.values())
                .map(Card::generateCard)
                .collect(Collectors.toList());
        return new Deck(cards);
    }

    public Card randomPick(NumberGenerator numberGenerator) {
        validateDeckIsNotEmpty();
        int cardIndex = numberGenerator.generateNumber();
        while (Boolean.FALSE.equals(isExist.get(cardIndex))) {
            cardIndex = numberGenerator.generateNumber();
        }
        isExist.set(cardIndex, false);
        return cards.get(cardIndex);
    }

    private void validateDeckIsNotEmpty() {
        if (Boolean.FALSE.equals(isExist.contains(true))) {
            throw new IllegalStateException(NO_AVAILABLE_CARD_EXCEPTION);
        }
    }
}
