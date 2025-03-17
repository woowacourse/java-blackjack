package blackjack.participant;

import blackjack.card.CardFixture;
import blackjack.result.Betting;

import java.util.function.Consumer;
import java.util.function.Function;

public class GameParticipantFixture {

    private static final Function<?, ?> ignoreFunction = ignore -> ignore;

    private static final Consumer<?> ignoreConsumer = ignore -> {
    };

    private static final Runnable ignoreRunnable = () -> {
    };


    public static Player createPlayer(String nickname, Betting betting, int sumOfCards) {
        Player player = createPlayer(nickname, betting);

        CardFixture.createCardsForSum(sumOfCards).getCards().forEach(
                player::drawCard
        );

        return player;
    }

    @SuppressWarnings("unchecked")
    public static Player createPlayer(String nickname) {

        return createPlayer(
                nickname,
                createBetting(0),
                (Function<Player, Boolean>) ignoreFunction);
    }

    @SuppressWarnings("unchecked")
    public static Player createPlayer(String nickname,
                                      Betting betting) {

        return createPlayer(
                nickname,
                betting,
                (Function<Player, Boolean>) ignoreFunction);
    }

    @SuppressWarnings("unchecked")
    public static Player createPlayer(String nickname,
                                      Betting betting,
                                      Function<Player, Boolean> hitDecision) {
        return createPlayer(
                nickname,
                betting,
                hitDecision,
                (Consumer<GameParticipant>) ignoreConsumer);
    }

    public static Player createPlayer(String nickname,
                                      Betting betting,
                                      Function<Player, Boolean> hitDecision,
                                      Consumer<GameParticipant> handDisplay) {

        return Player.of(
                Nickname.from(nickname),
                betting,
                hitDecision,
                handDisplay);
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

    public static Betting createBetting(int amount) {
        return Betting.from(amount);
    }
}
