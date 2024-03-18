package model.result;

import java.util.List;

public record ProfitsDto(ProfitDto dealerProfit, List<ProfitDto> playerProfits) {

}
