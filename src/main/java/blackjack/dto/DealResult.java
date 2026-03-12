package blackjack.dto;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Players;
import java.util.List;

public record DealResult(
        List<PlayerHandResult> playerHands,
        CardInfo dealerOpenCard
) {
    public static DealResult from(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        List<PlayerHandResult> playerHands = players.getPlayers().stream()
                .map(PlayerHandResult::from)
                .toList();
        CardInfo dealerCard = CardInfo.from(dealer.getOpenCard());
        return new DealResult(playerHands, dealerCard);
    }

    public String playersNames() {
        StringBuilder builder = new StringBuilder();
        for (PlayerHandResult handResult : playerHands) {
            builder.append(" ");
            builder.append(handResult.name());
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }
}