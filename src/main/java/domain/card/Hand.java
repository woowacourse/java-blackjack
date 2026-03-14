package domain.card;

import domain.card.vo.Card;
import domain.score.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Hand {
    public static final Score ACE_ADVANTAGE = new Score(10);
    public static final int MIN_SIZE = 2;
    private static final Score BUST_BOUND_SCORE = new Score(21);
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    public static Hand createFromDeck(Deck deck) {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, MIN_SIZE)
                .forEach((i) -> cards.add(deck.drawCard()));
        return new Hand(cards);
    }

    private void validateCardsSize(List<Card> cards) {
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException("처음 게임 시작 시, 두장을 나눠줘야한다.");
        }
    }

    public Score getScore() {
        Score score = cards.stream()
                .map(Card::getScore)
                .reduce(Score::sum)
                .orElseThrow(() -> new IllegalStateException("card 값을 더할 수 없어 getScore()를 실행할 수 없습니다."));

        if (hasAce(cards) && !isAceAdvantageIsBust(score)) {
            return score.sum(ACE_ADVANTAGE);
        }

        return score;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private boolean hasAce(List<Card> hands) {
        return hands.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isAceAdvantageIsBust(Score score) {
        if (!hasAce(cards)) {
            throw new IllegalStateException("Ace 가 없을때는 AceAdvantage 를 얻을 수 없습니다.");
        }
        return score.sum(ACE_ADVANTAGE).isHigher(BUST_BOUND_SCORE);
    }

    public boolean isBust() {
        return getScore().isHigher(BUST_BOUND_SCORE);
    }

    public int getSize() {
        return cards.size();
    }
}
