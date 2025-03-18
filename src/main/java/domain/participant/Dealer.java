package domain.participant;

import domain.TrumpCard;
import domain.participant.state.Started;
import domain.participant.state.State;
import java.util.List;

public class Dealer extends Participant {

    public Dealer(State state) {
        super(state);
    }

    public TrumpCard retrieveFirstCard() {
        List<TrumpCard> cards = super.state.retrieveCards();

        if (cards.size() != Started.INITIAL_CARD_COUNT) {
            throw new IllegalStateException("딜러는 " + Started.INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }

        return cards.getFirst();
    }
}
