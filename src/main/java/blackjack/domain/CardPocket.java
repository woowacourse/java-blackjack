package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardPocket {
    private static final int VALUE_ACE = 10;
    private static final int VALUE_BLACKJACK = 21;
    private final List<Card> cards;

    public CardPocket(final List<Card> cards) {
        validateCardPocket(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateCardPocket(final List<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("카드를 입력하지 않았습니다");
        }
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int countOfAce = countAce();
        int scoreOfCards = calculateMinimumScore();
        for (int i = 0; i < countOfAce; i++) {
            scoreOfCards = calculateAceScore(scoreOfCards);
        }
        return scoreOfCards;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.getSymbol() == Symbol.ACE)
                .count();
    }

    private int calculateMinimumScore() {
        return cards.stream()
                .map(Card::getSymbol)
                .mapToInt(Symbol::getScore)
                .sum();
    }

    //todo 메서드 네이밍, 로직 수정
    private int calculateAceScore(final int score) {
        if (score + VALUE_ACE > VALUE_BLACKJACK) {
            return score;
        }
        return score + VALUE_ACE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CardPocket that = (CardPocket) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
