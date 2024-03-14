package domain.blackjackgame;

import domain.card.Card;
import domain.card.Score;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startGame() {
        dealer.shuffleCardDeck();

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
        dealer.receiveAdditionalCard(dealerCard);
    }

    public void dealToPlayer(int playerIndex) {
        Card playerCard = dealer.pickCard();
        players.giveCardToPlayer(playerIndex, playerCard);
    }

    public GameResult createGameResult() {
        Score dealerScore = dealer.calculateScore();

        GameResult gameResult = new GameResult();
        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            Score playerScore = player.calculateScore();
            ResultStatus status = getResultStatus(playerScore, dealerScore);
            gameResult.record(player, status);
        }

        return gameResult;
    }

    private ResultStatus getResultStatus(Score playerScore, Score dealerScore) {
        if (playerScore.isBustScore()) {
            return ResultStatus.LOSE;
        }
        if (dealerScore.isBustScore()) {
            return ResultStatus.WIN;
        }
        if (playerScore.isGreaterThan(dealerScore)) {
            return ResultStatus.WIN;
        }
        if (playerScore.equals(dealerScore)) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    public boolean shouldDealerDrawCard() {
        return dealer.isNecessaryMoreCard();
    }
}
