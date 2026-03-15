package blackjack.fixture;

import blackjack.domain.Players;
import blackjack.dto.PlayerBettingRequest;

public class PlayersFixture {

    public static Players createValidSinglePlayer() {
        PlayerBettingRequest boyeRequest = PlayerBettingRequest.of("boye", "10000");
        return Players.makeEmptyPlayers().addPlayer(boyeRequest);
    }

    public static Players createValidTwoPlayers() {
        PlayerBettingRequest boyeRequest = PlayerBettingRequest.of("boye", "10000");
        PlayerBettingRequest suminRequest = PlayerBettingRequest.of("sumin", "20000");
        return Players.makeEmptyPlayers()
            .addPlayer(boyeRequest)
            .addPlayer(suminRequest);
    }
}
