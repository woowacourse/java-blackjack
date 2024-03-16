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

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canPickCard(final DecisionToContinue decision) {
        return calculateScore() <= CARD_PICK_THRESHOLD;
    }

    @Override
    public ParticipantHandStatus createInitialHandStatus() {
        List<Card> cardsExceptLastOne = new ArrayList<>(hand.getCards());
        cardsExceptLastOne.remove(cardsExceptLastOne.size() - 1);
        return new ParticipantHandStatus(name, new Hand(cardsExceptLastOne));
    }

    public ParticipantProfitResponse createDealerProfitResponse(final List<ParticipantProfitResponse> playersProfit) {
        int dealerProfit = playersProfit.stream()
                .mapToInt(response -> toDealerProfit(response.profit()))
                .sum();
        return new ParticipantProfitResponse(name, dealerProfit);
    }

    private int toDealerProfit(final int playerProfit) {
        return (-1) * playerProfit;
    }

    public void clear() {
        hand.clear();
    }
}
