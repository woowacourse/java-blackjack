package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameResult;
import blackjack.domain.participant.Player;

public record PlayerGameResult(
        String nickname,
        GameResult gameResult
) {
    
    public static PlayerGameResult of(Player player, Dealer dealer) {
        return new PlayerGameResult(
                player.getNickname(),
                dealer.determineGameResult(player).opposite()
        );
    }
}
