package domain;

import domain.state.GameState;

public enum GameResult {
    블랙잭(1.5),
    승(1),
    무(0),
    패(-1);

    private final double allocation;

    GameResult(double allocation) {
        this.allocation = allocation;
    }

    public double getAllocation() {
        return allocation;
    }

    public static GameResult decidePlayerResult(Player player, Dealer dealer) {
        GameState playerGameState = player.gameState;
        GameState dealerGameState = dealer.gameState;

        if (playerGameState.isBust()) {
            return GameResult.패;
        }
        if (dealerGameState.isBust()) {
            return GameResult.승;
        }
        if (playerGameState.isBlackJack() && dealerGameState.isBlackJack()) {
            return GameResult.무;
        }
        if (playerGameState.isBlackJack()) {
            return GameResult.블랙잭;
        }
        if (dealerGameState.isBlackJack()) {
            return GameResult.패;
        }
        return compareScoreForCheckPlayerResult(playerGameState.getCardsSum(), dealerGameState.getCardsSum());
    }

    private static GameResult compareScoreForCheckPlayerResult(int playerScore, int dealerScore) {
        if (dealerScore > playerScore) {
            return GameResult.패;
        }
        if (dealerScore == playerScore) {
            return GameResult.무;
        }
        return GameResult.승;
    }

    public GameResult reverse() {
        if (this.equals(GameResult.승)) {
            return GameResult.패;
        }

        if (this.equals(GameResult.패)) {
            return GameResult.승;
        }

        return GameResult.무;
    }
}