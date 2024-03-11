package controller;

import java.util.List;
import java.util.function.Supplier;
import model.blackjackgame.BlackjackGame;
import model.blackjackgame.GameResult;
import model.blackjackgame.HitAnswer;
import model.card.Card;
import model.card.RandomCard;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final RandomCard randomCardPicker = RandomCard.getRandomCard();

    public void run() {
        Players players = preparePlayers();
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        initGame(blackjackGame);
        executeGame(blackjackGame);
        finishGameWithResult(blackjackGame);
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private void initGame(BlackjackGame blackjackGame) {
        int cardCount = blackjackGame.determineInitCardCount();
        Cards cards = randomCardPicker.pickCards(cardCount);

        blackjackGame.initCards(cards);
        OutputView.printInitCards(blackjackGame);
    }

    private void executeGame(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getElements()) {
            continueHit(blackjackGame, player);
        }
        Card card = randomCardPicker.pickCard();
        if (blackjackGame.hitForDealer(card)) {
            OutputView.printAfterDealerHit();
        }
    }

    private void continueHit(BlackjackGame blackjackGame, Player player) {
        HitAnswer hitAnswer = new HitAnswer(true);
        if (player.isPossibleAddCard()) {
            hitAnswer = prepareHitAnswer(player);
        }
        while (player.isPossibleAddCard() && hitAnswer.isHit()) {
            player = hit(blackjackGame, player);
            OutputView.printPlayerCard(player);
            hitAnswer = prepareHitAnswer(player);
        }
    }

    private HitAnswer prepareHitAnswer(Player player) {
        return retryOnException(() -> {
            String hitAnswer = InputView.askHitAnswer(player);
            return HitAnswer.of(hitAnswer);
        });
    }

    private Player hit(BlackjackGame blackjackGame, Player player) {
        Card card = randomCardPicker.pickCard();
        return blackjackGame.hitForPlayer(player, card);
    }

    private void finishGameWithResult(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printFinalScore(dealer, players, gameResult);
        OutputView.printGameResult(gameResult);
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
