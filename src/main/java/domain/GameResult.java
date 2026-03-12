package domain;

public enum GameResult {
    승, 무, 패;

    public static GameResult decidePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return GameResult.패;
        }
        if (dealer.isBust()) {
            return GameResult.승;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return GameResult.무;
        }
        if (player.isBlackJack()) {
            return GameResult.승;
        }
        if (dealer.isBlackJack()) {
            return GameResult.패;
        }
        return compareScoreForCheckPlayerResult(dealer.getOwnCardsSum(), player.getOwnCardsSum());
    }

    private static GameResult compareScoreForCheckPlayerResult(int dealerScore, int playerScore) {
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
