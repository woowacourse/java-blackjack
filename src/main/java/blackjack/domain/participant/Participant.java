package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Score;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.result.Score.ACE_NUMBER;
import static blackjack.domain.result.Score.BLACKJACK_NUMBER;

public abstract class Participant {

    private final String name;
    private final List<Card> cards;

    protected Participant(String name, List<Card> cards) {
        Objects.requireNonNull(name, "이름은 null일 수 없습니다.");
        Objects.requireNonNull(cards, "카드는 null일 수 없습니다.");
        this.name = name;
        this.cards = cards;
    }

    abstract public List<Card> showFirstCards();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    protected Score calculateScore() {
        Score score = Score.from(cards);

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
        return calculateScore().isOver(BLACKJACK_NUMBER);
    }

    public int getScore() {
        return calculateScore().getValue();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name;
    }
}
