package domain.blackjackgame;

import domain.card.CardDeck;
import domain.card.CardShuffleStrategy;
import domain.card.Score;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class BlackjackGame {
    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;

    public BlackjackGame(CardDeck cardDeck, CardShuffleStrategy cardShuffleStrategy) {
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
    }

    public void initGame(Dealer dealer, Players players) {
        cardDeck.shuffle(cardShuffleStrategy);

        dealer.receiveInitialCards(cardDeck.draw(), cardDeck.draw());
        for (Player player : players.getPlayers()) {
            player.receiveInitialCards(cardDeck.draw(), cardDeck.draw());
        }
    }

    public void dealCardTo(Participant participant) {
        participant.receiveAdditionalCard(cardDeck.draw());
    }

    public GameResult createGameResult(Dealer dealer, Players players) {
        Score dealerScore = dealer.calculateScore();

        GameResult gameResult = new GameResult();
        for (Player player : players.getPlayers()) {
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
}
