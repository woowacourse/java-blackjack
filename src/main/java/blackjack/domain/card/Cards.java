package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Cards {

    private static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";
    private static final int BLACKJACK_CARD_HAND_COUNT = 2;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public void concat(Cards cards) {
        List<Card> tempCards = new ArrayList<>(this.cards);
        tempCards.addAll(cards.getCards());
        validateDuplicate(tempCards);
        this.cards.addAll(cards.getCards());
    }

    public Score getBestPossible() {
        if (hasAce()) {
            return Score.soft(extractedScores());
        }
        return Score.hard(extractedScores());
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private IntStream extractedScores() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(Number::getScore);
    }

    public boolean isBusted() {
        return getBestPossible().isBusted();
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_HAND_COUNT && getBestPossible().isMaxScore();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
