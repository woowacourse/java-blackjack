package blackjack.gamer;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.Nickname;
import blackjack.domain.gamer.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class GameParticipantFixture {

    private static final Function<?, ?> ignoreFunction = ignore -> ignore;

    private static final Consumer<?> ignoreConsumer = ignore -> {
    };

    private static final Runnable ignoreRunnable = () -> {
    };

    @SuppressWarnings("unchecked")
    public static Player createPlayer(String nickname) {
        return Player.of(Nickname.from(nickname), (Function<Player, Boolean>) ignoreFunction, (Consumer<GameParticipant>) ignoreConsumer);
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

    @SuppressWarnings("unchecked")
    public static Dealer createDealer(int playerCount) {
        return createDealer(playerCount, (Consumer<GameParticipant>) ignoreConsumer, ignoreRunnable);
    }

    public static Dealer createDealer(int playerCount,
                                      Consumer<GameParticipant> handDisplay) {
        return createDealer(playerCount, handDisplay, ignoreRunnable);
    }

    public static Dealer createDealer(int playerCount,
                                      Consumer<GameParticipant> handDisplay,
                                      Runnable hitDecisionDisplay) {
        return Dealer.create(playerCount, handDisplay, hitDecisionDisplay);
    }
}
