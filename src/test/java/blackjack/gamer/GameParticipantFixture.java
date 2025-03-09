package blackjack.gamer;

import java.util.Objects;
import java.util.function.Function;

public class GameParticipantFixture {

    public static Player createPlayer(String nickname) {
        return Player.of(Nickname.from(nickname), Objects::nonNull);
    }

    public static Player createPlayer(String nickname, Function<Player, Boolean> hitDecision) {
        return Player.of(Nickname.from(nickname), hitDecision);
    }

    public static Dealer createDealer() {
        return createDealer(1);
    }

    public static Dealer createDealer(int playerCount) {
        return Dealer.create(playerCount);
    }
}
