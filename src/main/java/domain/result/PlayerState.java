package domain.result;

import domain.Money;
import domain.PlayedGameResult;

class PlayerState {

    private final Money bettingMoney;
    private final PlayedGameResult playedGameResult;

    private PlayerState(Money bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.playedGameResult = PlayedGameResult.none();
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

    PlayedGameResult playedGameResult() {
        return playedGameResult;
    }

    Money evaluateProfitWith(PlayedGameResult dealerGameResult) {
        if(IsPlayerGameResultNone()) {
            throw new IllegalStateException("게임 결과가 기록되지 않았습니다.");
        }
        return determinePlayerGameOutcomeWith(dealerGameResult).calculate(bettingMoney);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeWith(PlayedGameResult dealerGameResult) {
        if (playedGameResult.isBusted()) {
            return PlayerGameOutcome.LOSE;
        }

        return determinePlayerGameOutcomeIfPlayerNotBusted(dealerGameResult);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfPlayerNotBusted(PlayedGameResult dealerGameResult) {
        if (playedGameResult.isBlackJack()) {
            return determinePlayerGameOutcomeIfPlayerIsBlackJack(dealerGameResult);
        }

        if (dealerGameResult.isBusted()) {
            return PlayerGameOutcome.WIN;
        }

        return determinePlayerGameOutcomeIfBothNotBusted(dealerGameResult);

    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfPlayerIsBlackJack(PlayedGameResult dealerGameResult) {
        if (dealerGameResult.isBlackJack()) {
            return PlayerGameOutcome.DRAW;
        }
        return PlayerGameOutcome.BLACK_JACK_WIN;
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfBothNotBusted(PlayedGameResult dealerGameResult) {
        int playerScoreSum = playedGameResult.scoreSum();
        int dealerScoreSum = dealerGameResult.scoreSum();

        return determinePlayerGameOutcomeIfBothNotBustedCompareWith(playerScoreSum, dealerScoreSum);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfBothNotBustedCompareWith(int playerScoreSum, int dealerScoreSum) {
        if (dealerScoreSum > playerScoreSum) {
            return PlayerGameOutcome.LOSE;
        }

        if (dealerScoreSum == playerScoreSum) {
            return PlayerGameOutcome.DRAW;
        }

        return PlayerGameOutcome.WIN;
    }

    private boolean IsPlayerGameResultNone() {
        return playedGameResult.isNone();
    }
}
