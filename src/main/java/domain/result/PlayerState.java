package domain.result;

import domain.common.Money;
import dto.PlayedGameResult;

public class PlayerState {

    private final Money bettingMoney;
    private final PlayedGameResult playedGameResult;

    private PlayerState(Money bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.playedGameResult = null;
    }

    private PlayerState(Money bettingMoney, PlayedGameResult playedGameResult) {
        this.bettingMoney = bettingMoney;
        this.playedGameResult = playedGameResult;
    }

    static PlayerState onlyBet(Money bettingMoney) {
        System.out.println(bettingMoney.amount());
        return new PlayerState(bettingMoney);
    }

    public PlayerState updatePlayedGameResult(PlayedGameResult playedGameResult) {
        return new PlayerState(this.bettingMoney, playedGameResult);
    }

    public PlayedGameResult playedGameResult() {
        return playedGameResult;
    }
}
