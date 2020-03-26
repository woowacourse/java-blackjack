package domain.card;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayingCards {
    private static final int BLACK_JACK = 21;
    private static final int MIN_SIZE = 2;

    private final Cards cards;

    private PlayingCards(List<Card> cards) {
        this.cards = Cards.of(cards);
    }

    private PlayingCards(Cards cards) {
        this.cards = cards;
    }

    public static PlayingCards of(List<Card> cards) {
        if (cards.size() < MIN_SIZE) {
            throw new IllegalArgumentException(String.format("카드의 개수가 최소 갯수인 %s보다 작습니다.", MIN_SIZE));
        }

        return new PlayingCards(cards);
    }

    public static PlayingCards of(Cards cards) {
        return of(cards.getCards());
    }

    public PlayingCards add(Card card) {
        return of(cards.add(card));
    }

    public PlayingCards add(Cards cardsToAdd) {
        return new PlayingCards(cards.add(cardsToAdd));
    }

    public int calculate() {
        int sumWithoutAce = cards.calculateSumExceptAce();
        return calculateSumWithAces(sumWithoutAce);
    }

    public boolean isBust() {
        return BLACK_JACK < calculate();
    }

    public boolean isSameSize(int size) {
        return cards.size() == size;
    }

    public List<String> serialize() {
        return cards.serialize();
    }

    public int size() {
        return cards.size();
    }

    private int calculateSumWithAces(int sum) {
        if (cards.hasAce()) {
            Cards aces = getAces();
            return calculateSumWithAces(sum, aces);
        }
        return sum;
    }

    private int calculateSumWithAces(int sum, Cards aces) {
        if (aces.hasCardNotAce()) {
            throw new InvalidStateException("복수의 에이스 카드 내에 부적절한 카드가 존해합니다.");
        }
        for (Card card : aces.getCards()) {
            int score = card.calculate(sum);
            sum += score;
        }
        return sum;
    }

    private Cards getAces() {
        List<Card> aces = this.cards.getCards()
                .stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
        if (aces.isEmpty()) {
            throw new InvalidStateException(String.format("%s가 존재하지 않습니다.", Symbol.ACE.getPattern()));
        }
        return Cards.of(aces);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCards that = (PlayingCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
