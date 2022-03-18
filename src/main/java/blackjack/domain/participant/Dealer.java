package blackjack.domain.participant;

import blackjack.dto.ProfitDto;

import java.util.List;

public class Dealer extends Participant {

    private static final int BOUND_FOR_ADDITIONAL_CARD = 16;
    private static final String NAME_OF_DEALER = "딜러";

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    public boolean isHittable() {
        return score <= BOUND_FOR_ADDITIONAL_CARD;
    }

    public ProfitDto computeProfit(List<ProfitDto> profitOfPlayers) {
        double totalProfitOfPlayers = profitOfPlayers.stream().mapToDouble(ProfitDto::getProfit).sum();
        return new ProfitDto(this, totalProfitOfPlayers * (-1));
    }

    public int getBound() {
        return BOUND_FOR_ADDITIONAL_CARD;
    }

}
