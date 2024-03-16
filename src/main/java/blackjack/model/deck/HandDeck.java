package blackjack.model.deck;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.List;

public class HandDeck {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        validateDuplicatedCard(card);
        cards.add(card);
    }

    public int calculateCardScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int countElevenAce() {
        return (int) cards.stream()
                .filter(Card::isElevenAce)
                .count();
    }

    public void switchAceValueInRow() {
        cards.stream()
                .filter(Card::isElevenAce)
                .findFirst()
                .ifPresent(Card::switchAceValue);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    private void validateDuplicatedCard(Card card) {
        if (cards.contains(card)) {
            String cardNumber = card.number().getName();
            String cardPattern = card.pattern().getName();
            String errorMessage = String.format("[ERROR] 중복된 카드는 받을 수 없습니다.(중복된 카드 : %s%s)", cardNumber, cardPattern);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
