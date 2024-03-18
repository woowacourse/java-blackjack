package domain.participant;

import controller.dto.response.ParticipantHandStatus;
import controller.dto.response.ParticipantProfitResponse;
import domain.card.Card;
import domain.game.DecisionToContinue;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int CARD_PICK_THRESHOLD = 16;
    private static final int MULTIPLIED_NUMBER_FOR_OPPOSITE_SIGN = -1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canPickCard(final DecisionToContinue decision) {
        return scoreboard.resultScore() <= CARD_PICK_THRESHOLD;
    }

    @Override
    public ParticipantHandStatus createInitialHandStatus() {
        List<Card> cardsExceptLastOne = new ArrayList<>(scoreboard.showCards());
        cardsExceptLastOne.remove(cardsExceptLastOne.size() - 1);
        return scoreboard.generateParticipantHandStatus(cardsExceptLastOne);
    }

    public int calculateDealerProfit(final List<ParticipantProfitResponse> playersProfit) {
        return playersProfit.stream()
                .mapToInt(response -> toDealerProfit(response.profit()))
                .sum();
    }

    public int toDealerProfit(final int playerProfit) {
        return MULTIPLIED_NUMBER_FOR_OPPOSITE_SIGN * playerProfit;
    }
}
