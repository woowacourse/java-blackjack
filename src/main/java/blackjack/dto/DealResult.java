package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import java.util.List;

public record DealResult(
        List<PlayerHandResult> playerHands,
        CardInfo dealerOpenCard
) {
    public static DealResult from(Players players, Dealer dealer) {
        List<PlayerHandResult> playerHands = players.getPlayers().stream()
                .map(PlayerHandResult::from)
                .toList();
        CardInfo dealerCard = CardInfo.from(dealer.getOpenCard());
        return new DealResult(playerHands, dealerCard);
    }
}
