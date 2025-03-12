package blackjack.domain.gamer;

import blackjack.domain.card.CardFixture;

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

    public static Player createPlayer(String nickname, int sumOfCards) {
        Player player = createPlayer(nickname);

        CardFixture.createCardsForSum(sumOfCards).getCards().forEach(
                player::drawCard
        );

        return player;
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
        return createDealer(playerCount, ignoreRunnable);
    }

    public static Dealer createDealer(int playerCount, int sumOfCards) {
        Dealer dealer = createDealer(playerCount);

        CardFixture.createCardsForSum(sumOfCards).getCards().forEach(
                dealer::drawCard
        );

        return dealer;
    }

    public static Dealer createDealer(int playerCount,
                                      Runnable hitDecisionDisplay) {
        return Dealer.create(playerCount, hitDecisionDisplay);
    }
}
