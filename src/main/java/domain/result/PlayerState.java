package domain.result;

import domain.Money;
import domain.PlayedGameResult;

class PlayerState {

    private final Money bettingMoney;
    private PlayedGameResult playedGameResult;

    private PlayerState(Money bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    private PlayerState(Money bettingMoney, PlayedGameResult playedGameResult) {
        this.bettingMoney = bettingMoney;
        this.playedGameResult = playedGameResult;
    }

    static PlayerState onlyBet(Money bettingMoney) {
        return new PlayerState(bettingMoney);
    }

    PlayerState updatePlayedGameResult(PlayedGameResult playedGameResult) {
        return new PlayerState(this.bettingMoney, playedGameResult);
    }

    Money bettingMoney() {
        return bettingMoney;
    }

    PlayedGameResult playedGameResult() {
        return playedGameResult;
    }

    boolean isBusted() {
        return playedGameResult.isBusted();
    }

    boolean isBlackJack() {
        return playedGameResult.isBlackJack();
    }

    int scoreSum() {
        return playedGameResult.scoreSum();
    }
}
