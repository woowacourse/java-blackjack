package domain.state;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.generator.FinishedStateGenerator;
import domain.state.running.Hit;
import java.util.List;

public abstract class Started implements State {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    public static State getStartState(List<FinishedStateGenerator> finishedStateGenerators, Hand hand) {
        for (FinishedStateGenerator finishedStateGenerator : finishedStateGenerators) {
            if (finishedStateGenerator.supports(hand)) {
                return finishedStateGenerator.create(hand);
            }
        }
        return new Hit(hand);
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
