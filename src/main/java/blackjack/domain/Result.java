package blackjack.domain;

import java.util.Map;

public class Result {

    private final DealerResult dealerResult;
    private final PlayersResult playersResult;

    public Result() {
        this.dealerResult = new DealerResult();
        this.playersResult = new PlayersResult();
    }

    public static Result of(Players players, Dealer dealer) {
        Result result = new Result();
        players.saveResult(dealer, result);

        return result;
    }

    public void save(Player player, GameResultType gameResultType) {
        // Player에게 첫 배팅 금액을 알아내고 (initBetting)
        // Player와 Dealer에게 GameResultType 와 함께 넘겨주어 월렛을 가산 감사한다.

        GameResultType gameResultTypeOfDealer = gameResultType.getOppositeType();

        playersResult.save(player, gameResultType);
        dealerResult.addCountOf(gameResultTypeOfDealer);
    }

    // 딜러와 플레이어들의 결과를 반환한다.

    public Map<GameResultType, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }

    public Map<Player, GameResultType> getPlayersResult() {
        return playersResult.getAllResult();
    }
}
