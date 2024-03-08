package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.common.Name;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultStatus;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

//TODO : Player가 아닌 GamePlayer를 상속하면 안되나?
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

        if (gamePlayer.isBust()) {
            return ResultStatus.LOSE;
        }
        if (isBust()) {
            return ResultStatus.WIN;
        }
        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }

    @Override
    public boolean isReceivable() {
        //TODO : 딜러는 A를 11로 인식해야 한다.
        //TODO : calculateScore 는 Cards 에 분리 할지 고민
        return cards.sum() <= RECEIVE_SIZE;
    }
}
