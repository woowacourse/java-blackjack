package blackjack.model.player;

import blackjack.model.card.Card;
import java.util.List;

public class Dealer extends Player {

    public static final int RECEIVABLE_POINT = 16;

    public boolean isDrawable() {
        return calculatePoint() <= RECEIVABLE_POINT;
    }

    public double calculateProfitAmount(Participants participants) {
        return participants.stream()
                .mapToDouble(participant -> participant.calculateProfitAmount(participant.duelWith(this)))
                .reduce(0, (total, profitAmount) -> total - profitAmount);
    }

    @Override
    public List<Card> getInitialCards() {
        return List.of(getReceivedCards().getFirstCard());
    }
}
