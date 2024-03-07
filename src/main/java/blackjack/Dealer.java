package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player implements CardReceivable {
    private static final Integer RECEIVE_SIZE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";


    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public static Dealer createDefaultDealer(Cards cards) {
        return new Dealer(new Name(DEFAULT_DEALER_NAME), cards);
    }

    public Result checkResult(List<GamePlayer> gamePlayers) {
        List<GamePlayerResult> gamePlayerResults = new ArrayList<>();

        for (GamePlayer gamePlayer : gamePlayers) {
            gamePlayerResults.add(new GamePlayerResult(gamePlayer.name, checkPlayer(gamePlayer)));
        }

        return new Result(gamePlayerResults, DealerResult.of(name, gamePlayerResults));
    }

    private ResultStatus checkPlayer(GamePlayer gamePlayer) {
        int playerScore = gamePlayer.calculateScore();
        int dealerScore = calculateScore();

        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }


    @Override
    public boolean isReceivable() {
        return cards.sum() <= RECEIVE_SIZE;
    }

}
