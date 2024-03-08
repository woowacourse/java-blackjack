package domain.blackjackgame;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startGame() {
        dealer.shuffle();

        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
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
            Player player = players.findPlayerByIndex(i);
            int playerScore = player.calculateScore();
            ResultStatus status = getResultStatus(dealerScore, playerScore);
            gameResult.record(player, status);
        }

        return gameResult;
    }

    private ResultStatus getResultStatus(int dealerScore, int playerScore) {
        if (playerScore > BLACKJACK_SCORE) {
            return ResultStatus.LOSE;
        }
        if (dealerScore > BLACKJACK_SCORE) {
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

    public boolean shouldDealerDrawCard() {
        return dealer.isNecessaryMoreCard();
    }
}
