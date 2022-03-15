package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Score;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.result.Score.ACE_NUMBER;
import static blackjack.domain.result.Score.BLACKJACK_NUMBER;

public abstract class Participant {

    public static final int INITIAL_DRAW_COUNT = 2;
    private final String name;
    private final List<Card> cards;

    protected Participant(String name, List<Card> cards) {
        Objects.requireNonNull(name, "이름은 null일 수 없습니다.");
        Objects.requireNonNull(cards, "카드는 null일 수 없습니다.");
        this.name = name;
        this.cards = cards;
    }

    public abstract List<Card> showFirstCards();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public Score calculateScore() {
        int sumCardNumbers = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        Score score = new Score(sumCardNumbers);

        if (hasAceCard()) {
            score = score.calculateWithAce();
        }

        return score;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == ACE_NUMBER);
    }

    public boolean isBust() {
        return calculateScore().isOver(new Score(BLACKJACK_NUMBER));
    }

    public boolean isBlackjack() {
        return calculateScore().equals(new Score(BLACKJACK_NUMBER)) &&
                 cards.size() == INITIAL_DRAW_COUNT;
    }

    public boolean hasHigherScoreThan(Participant participant) {
        return this.calculateScore().isOver(participant.calculateScore());
    }

    public boolean hasSameScoreWith(Participant participant) {
        return this.calculateScore().equals(participant.calculateScore());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name;
    }
}
