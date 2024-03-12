package controller;

import java.util.List;
import java.util.function.Supplier;
import model.blackjackgame.BlackjackGame;
import model.blackjackgame.GameResult;
import model.blackjackgame.HitAnswer;
import model.card.Card;
import model.card.Hand;
import model.card.RandomCard;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
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
        Hand cards = RandomCard.pickCards(cardCount);

        blackjackGame.initCards(cards);
        OutputView.printInitCards(blackjackGame);
    }

    private void hitToPlayers(BlackjackGame blackjackGame) {
        blackjackGame.getPlayersGroup()
            .forEach(player -> hitUntilStay(blackjackGame, player));
    }

    private void hitUntilStay(BlackjackGame blackjackGame, Player player) {
        while (player.isPossibleHit() && prepareHitAnswer(player).isHit()) {
            player = hit(blackjackGame, player);
            OutputView.printPlayerCard(player);
        }
    }

    private HitAnswer prepareHitAnswer(Player player) {
        return retryOnException(() -> {
            String hitAnswer = InputView.askHitAnswer(player);
            return HitAnswer.of(hitAnswer);
        });
    }

    private Player hit(BlackjackGame blackjackGame, Player player) {
        Card card = RandomCard.pickCard();
        return blackjackGame.playerHit(player, card);
    }

    private void hitToDealer(BlackjackGame blackjackGame) {
        if (blackjackGame.isDealerPossibleHit()) {
            Card card = RandomCard.pickCard();
            blackjackGame.dealerHit(card);
            OutputView.printAfterDealerHit();
        }
    }

    private void finishGame(BlackjackGame blackjackGame) {
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
