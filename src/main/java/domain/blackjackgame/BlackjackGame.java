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
            ResultStatus status = getResultStatusByScore(dealer, player);
            gameResult.record(player, status);
        }
        return gameResult;
    }

    private ResultStatus getResultStatusByScore(Dealer dealer, Player player) {
        if (player.isBust()) {
            return ResultStatus.LOSE;
        }
        if (dealer.isBust()) {
            return ResultStatus.WIN;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return ResultStatus.DRAW;
        }
        if (player.isBlackjack()) {
            return ResultStatus.WIN;
        }
        if (dealer.isBlackjack()) {
            return ResultStatus.LOSE;
        }
        return getResultStatusByScore(player.calculateScore(), dealer.calculateScore());
    }

    private ResultStatus getResultStatusByScore(Score playerScore, Score dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return ResultStatus.WIN;
        }
        if (playerScore.equals(dealerScore)) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }
}
