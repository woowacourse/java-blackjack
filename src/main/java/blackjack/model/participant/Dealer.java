package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.result.PlayerResult;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_PICK_THRESHOLD = 16;

    private Dealer(final Hands hands) {
        super(DEALER_NAME, hands);
    }

    public static Dealer create() {
        return new Dealer(Hands.empty());
    }

    @Override
    public List<CardDto> getInitCards() {
        return List.of(hands.getFirstCard());
    }

    public boolean canPick() {
        return !hands.hasScoreHigherThan(DEALER_PICK_THRESHOLD);
    }

    public PlayerResult getPlayerResult(final Player player) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }

        if (this.isBust()) {
            return PlayerResult.WIN;
        }

        if (this.hasHigherScoreThan(player)) {
            return PlayerResult.LOSE;
        }

        if (player.hasHigherScoreThan(this)) {
            return PlayerResult.WIN;
        }

        return PlayerResult.DRAW;
    }
}
