package dto.result;

import java.util.List;

public record ProfitResult(ParticipantProfit dealerProfit, List<ParticipantProfit> playerProfit) {}
