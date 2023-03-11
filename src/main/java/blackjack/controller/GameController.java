package blackjack.controller;

import blackjack.model.BlackjackGame;
import blackjack.model.participant.Players;
import blackjack.model.WinningResult;
import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

public class GameController {

    public static final String BLACKJACK_MESSAGE = " (블랙잭!!)";

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame game = new BlackjackGame(new Players(inputView.readNames()), new Dealer());
        CardDeck cardDeck = new CardDeck();

        distributeFirstCards(game, cardDeck);

        playHitOrStand(game, cardDeck);

        printScoreResults(game);
        printWinningResult(game);
    }

    private void distributeFirstCards(BlackjackGame game, CardDeck cardDeck) {
        game.distributeCards(cardDeck);
        outputView.printDistributionMessage(game.getPlayerNames());
        outputView.printHandCardUnits(game.getDealerFirstDistributed());
        outputView.printHandCardUnits(game.getPlayersFirstDistributed());
    }

    private void playHitOrStand(BlackjackGame game, CardDeck cardDeck) {
        for (int id : game.getPlayerIds()) {
            playerHitOrStand(game, cardDeck, id);
        }
        dealerHitOrStand(game, cardDeck);
    }

    private void dealerHitOrStand(BlackjackGame game, CardDeck cardDeck) {
        while (!game.isDealerFinished()) {
            game.drawDealerCard(cardDeck);
            outputView.printDealerHitMessage();
        }
    }

    private void playerHitOrStand(BlackjackGame game, CardDeck cardDeck, int playerId) {
        while (!game.isPlayerFinished(playerId) && inputView.readIsHit(game.getPlayerName(playerId))) {
            game.drawPlayerCard(cardDeck, playerId);
            outputView.printHandCardUnits(game.getPlayerNameHandCard(playerId));
        }
    }

    private void printScoreResults(BlackjackGame game) {
        outputView.printScoreResult(game.getDealerNameHand(), game.getDealerScoreResult(BLACKJACK_MESSAGE));

        for (int playerId : game.getPlayerIds()) {
            outputView.printScoreResult(game.getPlayerNameHandCard(playerId), game.getPlayerScoreResult(playerId, BLACKJACK_MESSAGE));
        }
    }

    private void printWinningResult(BlackjackGame game) {
        Map<String, WinningResult> results = game.participantWinningResults();

        outputView.printWinningResultMessage();
        outputView.printDealerWinningResult(results.remove(game.getDealerName()).getResult());
        for (Map.Entry<String, WinningResult> playerResult : results.entrySet()) {
            WinningResult playerWinning = playerResult.getValue();
            outputView.printPlayersWinningResult(playerResult.getKey(), playerWinning.getResult());
        }
    }
}
