package dto;

import domain.gamer.Dealer;
import java.util.List;

public record DealerResultDto(List<String> dealerCards, int dealerScore) {
    public static DealerResultDto makeDealerResultDto(Dealer dealer) {
        return new DealerResultDto(dealer.getCardStatus(), dealer.getMaxGameScore());
    }
}
