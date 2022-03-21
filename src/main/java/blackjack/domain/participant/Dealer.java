package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.state.DealerRunning;
import java.util.List;

public class Dealer extends Participant {

    private static final int HIT_STANDARD = 17;

    public Dealer(List<Card> cards) {
        super(new Name("딜러"), DealerRunning.start(cards));
    }

    @Override
    public List<Card> showInitialCards() {
        return List.of(getCards().get(0));
    }
}
