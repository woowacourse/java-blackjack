package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldingCards {

    private static final int ACE_ADDITIONAL_POINT = 10;

    private final List<Card> cards = new ArrayList<>();

    public void initialCard(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score getSum() {
        Score score = getDefaultSum();
        if (!hasAce()) {
            return score;
        }
        return getSumOfContainingAce(score);
    }

    public Score getDefaultSum() {
        Score score = Score.min();
        for (Card card : cards) {
            int point = card.getPoint();
            score = score.plus(new Score(point));
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private Score getSumOfContainingAce(Score score) {
        Score scoreOfAddingAceAdditionalPoint = score.plus(new Score(10));
        if (scoreOfAddingAceAdditionalPoint.isBust()) {
            return score;
        }
        return scoreOfAddingAceAdditionalPoint;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
