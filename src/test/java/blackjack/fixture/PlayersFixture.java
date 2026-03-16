package blackjack.fixture;

import blackjack.domain.Nickname;
import blackjack.domain.Players;

public class PlayersFixture {

    public static Players createValidSinglePlayer() {
        Nickname nickname = Nickname.from("boye");
        return Players.makeEmptyPlayers().addPlayer(nickname, 10000);
    }

    public static Players createValidTwoPlayers() {
        Nickname boyeNickname = Nickname.from("boye");
        Nickname suminNickname = Nickname.from("sumin");
        return Players.makeEmptyPlayers()
            .addPlayer(boyeNickname, 10000)
            .addPlayer(suminNickname, 20000);
    }
}
