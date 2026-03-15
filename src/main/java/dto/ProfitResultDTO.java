package dto;

import java.util.Map;
import vo.Money;

public class ProfitResultDTO {
    private final Money dealerProfit;
    private final Map<String, Money> participantsProfit;

    public ProfitResultDTO(Money dealerProfit, Map<String, Money> participantsProfit) {
        this.dealerProfit = dealerProfit;
        this.participantsProfit = participantsProfit;
    }

    public Money getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Money> getParticipantsProfit() {
        return participantsProfit;
    }
}
