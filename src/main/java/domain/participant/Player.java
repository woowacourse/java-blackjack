package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;
import java.util.List;

public class Player {

    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final Hand hand;

    private Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player withName(String rawName) {
        return new Player(new Name(rawName), new Hand());
    }

    public void tryReceive(Card card) {
        if (score().toInt() <= BLACKJACK_SCORE) {
            hand.add(card);
        }
    }

    public void tryReceive(List<Card> cards) {
        if (score().toInt() <= BLACKJACK_SCORE) {
            hand.add(cards);
        }
    }

    public boolean isBust() {
        return score().isBust();
    }

    public boolean isNotBust() {
        return score().isNotBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean hasHigherScoreThan(Dealer dealer) {
        return score().isGreaterThan(dealer.score());
    }

    public boolean hasLowerScoreThan(Dealer dealer) {
        return score().isLessThan(dealer.score());
    }

    public Score score() {
        return hand.totalScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
