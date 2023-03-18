package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Hand {

    private static final Score step = new Score(10);
    private static final Score max = new Score(21);
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand(final Card... cards) {
        this.cards = new ArrayList<>(List.of(cards));
    }

    private Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand addCard(final Card card) {
        cards.add(card);
        return new Hand(new ArrayList<>(cards));
    }

    public Score getScore() {
        Score sumScore = getSumScore();
        final List<Card> aceCards = getAceCards();

        while (sumScore.isMoreThen(max) && !aceCards.isEmpty()) {
            sumScore = sumScore.minus(step);
            aceCards.remove(0);
        }

        return sumScore;
    }

    private Score getSumScore() {
        return new Score(cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getScore)
                .sum());
    }

    private List<Card> getAceCards() {
        return cards.stream()
                .filter(card -> CardNumber.ACE.equals(card.getNumber()))
                .collect(Collectors.toList());
    }

    public boolean isBust() {
        return getScore().isMoreThen(new Score(21));
    }

    public boolean isBlackjack() {
        return max.equals(getScore()) && cards.size() == BLACKJACK_CARD_SIZE;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
