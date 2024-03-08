package domain;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startGame() {
        dealer.shuffle();

        for (int count = 0; count < 2; count++) {
            dealAllParticipants();
        }
    }

    private void dealAllParticipants() {
        dealToDealer();

        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            dealToPlayer(playerIndex);
        }
    }

    public void dealToDealer() {
        Card dealerCard = dealer.pickCard();
        dealer.add(dealerCard);
    }

    public void dealToPlayer(int playerIndex) {
        Card playerCard = dealer.pickCard();
        players.giveCardToPlayer(playerIndex, playerCard);
    }

    public GameResult createGameResult() {
        int dealerScore = dealer.calculateScore();

        GameResult gameResult = new GameResult();
        for (int i = 0; i < players.count(); i++) {
            Player player = players.getPlayerByIndex(i);
            int playerScore = player.calculateScore();
            ResultStatus status = getResultStatus(dealerScore, playerScore);
            gameResult.record(player, status);
        }

        return gameResult;
    }

    private ResultStatus getResultStatus(int dealerScore, int playerScore) {
        if (playerScore > 21) {
            return ResultStatus.LOSE;
        } else if (dealerScore > 21) {
            return ResultStatus.WIN;
        } else if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        } else if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    public boolean shouldDealerDrawCard() {
        return dealer.isNecessaryMoreCard();
    }
}
