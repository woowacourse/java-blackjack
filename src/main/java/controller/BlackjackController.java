package controller;

import java.util.List;
import java.util.function.Supplier;
import model.blackjackgame.BlackjackGame;
import model.blackjackgame.HitAnswer;
import model.card.Card;
import model.card.RandomCard;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayersResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Players players = preparePlayers();
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        initGame(blackjackGame);
        hitToPlayers(blackjackGame);
        hitToDealer(blackjackGame);
        finishGame(blackjackGame);
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private void initGame(BlackjackGame blackjackGame) {
        int cardCount = blackjackGame.determineInitCardCount();
        List<Card> cards = RandomCard.pickCards(cardCount);
        blackjackGame.initHand(cards);
        OutputView.printInitHand(blackjackGame);
    }

    private void hitToPlayers(BlackjackGame blackjackGame) {
        blackjackGame.getPlayerGroup()
            .forEach(this::hitUntilStay);
    }

    private void hitUntilStay(Player player) {
        while (player.isPossibleHit() && prepareHitAnswer(player).isHit()) {
            Card card = RandomCard.pickCard();
            player.hitCard(card);
            OutputView.printPlayerCard(player);
        }
    }

    private HitAnswer prepareHitAnswer(Player player) {
        return retryOnException(() -> {
            String hitAnswer = InputView.askHitAnswer(player);
            return HitAnswer.of(hitAnswer);
        });
    }

    private void hitToDealer(BlackjackGame blackjackGame) {
        if (blackjackGame.isDealerPossibleHit()) {
            Card card = RandomCard.pickCard();
            blackjackGame.dealerHit(card);
            OutputView.printAfterDealerHit(blackjackGame.getDealer());
        }
    }

    private void finishGame(BlackjackGame blackjackGame) {
        GameResult gameResult = blackjackGame.finishGame();
        DealerResult dealerResult = DealerResult.from(gameResult);
        PlayersResult playersResult = PlayersResult.from(gameResult);

        OutputView.printFinalScore(blackjackGame, gameResult);
        OutputView.printGameResult(dealerResult, playersResult);
    }

    private static <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
