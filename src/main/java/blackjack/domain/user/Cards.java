package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {
    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getOneCard() {
        return cards.get(FIRST_CARD);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score totalScore() {
        Score totalScore = sum();
        int aceCount = getAceCount();
        for (int i = 0; i < aceCount; i++) {
            totalScore = totalScore.decideScoreByStatus();
        }
        return totalScore;
    }

    private Score sum() {
        return new Score(cards.stream()
                .mapToInt(Card::getDenomination)
                .sum());
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public boolean isSoftHand() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public boolean isBust() {
        return totalScore().isBust();
    }

    public boolean isBlackjack() {
        return totalScore().isBlackjack();
    }

    public Cards getCardsByCount(int count) {
        try {
            return IntStream.range(FIRST_CARD, count)
                    .mapToObj(cards::get)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("[ERROR] 카드 갯수보다 많은 카드를 가져올 수 없습니다.");
        }
    }
}
