package domain.participant;

import domain.card.Card;
import domain.card.CardsSnapshot;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    public static final int BUST_THRESHOLD = 21;
    public static final int BLACKJACK_SCORE = 21;
    private static final int NATURAL_CARDS_COUNT = 2;

    private final Name name;
    private final Hand hand;

    protected Participant(Name name) {
        this.hand = new Hand();
        this.name = name;
    }

    public void add(Card card) {
        hand.add(card);
    }

    //TODO distributeCards로 수정
    public void addAll(List<Card> cards) {
        hand.addAll(cards);
    }

    public CardsSnapshot handInfo() {
        return hand.snapshot();
    }

    public CardsSnapshot firstCardInfo() {
        return hand.firstCardSnapshot();
    }

    public List<Card> cardsInHand() {
        return hand.cards();
    }

    public Card firstCardInHand() {
        return hand.firstCard();
    }

    public int cardsCount() {
        return hand.cardsCount();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean hasScoreHigherThan(Participant otherParticipant) {
        return this.getScore() > otherParticipant.getScore();
    }

    public boolean hasScoreSameAs(Participant otherParticipant) {
        return this.getScore() == otherParticipant.getScore();
    }

    public boolean isBust() {
        return getScore() > BUST_THRESHOLD;
    }

    public boolean isNatural() {
        return (getScore() == BLACKJACK_SCORE) && (cardsCount() == NATURAL_CARDS_COUNT);
    }

    public String name() {
        return name.value();
    }

    public Name getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant participant = (Participant) o;
        return Objects.equals(name, participant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
