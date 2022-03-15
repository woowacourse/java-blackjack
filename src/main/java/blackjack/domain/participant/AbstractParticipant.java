package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Score;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.result.Score.ACE_NUMBER;
import static blackjack.domain.result.Score.BLACKJACK_NUMBER;

public abstract class AbstractParticipant implements Participant {

    public static final int INITIAL_DRAW_COUNT = 2;

    private final String name;
    private final List<Card> cards;

    protected AbstractParticipant(final String name, final List<Card> cards) {
        Objects.requireNonNull(name, "이름은 null일 수 없습니다.");
        Objects.requireNonNull(cards, "카드는 null일 수 없습니다.");
        this.name = name;
        this.cards = cards;
    }

    public abstract List<Card> showFirstCards();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    @Override
    public boolean isBust() {
        return calculateScore().isOver(new Score(BLACKJACK_NUMBER));
    }

    @Override
    public boolean isBlackjack() {
        return calculateScore().equals(new Score(BLACKJACK_NUMBER)) &&
                 cards.size() == INITIAL_DRAW_COUNT;
    }

    @Override
    public boolean hasHigherScoreThan(final AbstractParticipant abstractParticipant) {
        return this.calculateScore().isOver(abstractParticipant.calculateScore());
    }

    @Override
    public boolean hasSameScoreWith(final AbstractParticipant abstractParticipant) {
        return this.calculateScore().equals(abstractParticipant.calculateScore());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Score calculateScore() {
        final int sumCardNumbers = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        Score score = new Score(sumCardNumbers);

        if (hasAceCard()) {
            score = score.calculateWithAce();
        }

        return score;
    }

    @Override
    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == ACE_NUMBER);
    }
}
