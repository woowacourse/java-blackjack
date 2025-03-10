package blackjack.gamer;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.Nickname;
import blackjack.domain.gamer.Player;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameParticipantFixture {

    public static Player createPlayer(String nickname) {
        return Player.of(Nickname.from(nickname), Objects::nonNull, System.out::println);
    }

    public static Player createPlayer(String nickname,
                                      Function<Player, Boolean> hitDecision) {
        return Player.of(Nickname.from(nickname), hitDecision, System.out::println);
    }

    public static Player createPlayer(String nickname,
                                      Function<Player, Boolean> hitDecision,
                                      Consumer<GameParticipant> handDisplay) {
        return Player.of(Nickname.from(nickname), hitDecision, handDisplay);
    }

    public static Dealer createDealer() {
        return createDealer(1);
    }

    public static Dealer createDealer(int playerCount) {
        return Dealer.create(playerCount, System.out::println);
    }

    public static Dealer createDealer(int playerCount,
                                      Consumer<GameParticipant> handDisplay) {
        return Dealer.create(playerCount, handDisplay);
    }
}
