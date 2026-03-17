package blackjack.view.dto;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Players;
import blackjack.service.dto.GameState;
import java.util.List;

public record DistributionCompleteOutputRequest(
        String dealerName,
        List<String> playerNames
) {
    public static DistributionCompleteOutputRequest from(GameState gameState) {
        Players players = gameState.players();
        Dealer dealer = gameState.dealer();

        return new DistributionCompleteOutputRequest(
                dealer.getName(),
                players.getNames()
        );
    }
}
