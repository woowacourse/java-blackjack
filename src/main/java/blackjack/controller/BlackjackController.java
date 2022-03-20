package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.CardDeck;
import blackjack.domain.CardDeckGenerator;
import blackjack.domain.DrawStatus;
import blackjack.domain.ParticipantResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final BlackjackGame blackjackGame = setUpBlackjackGame();
        playGame(blackjackGame);
        announceGameResult(blackjackGame);
    }

    private BlackjackGame setUpBlackjackGame() {
        final BlackjackGame blackjackGame = BlackjackGame.create(setUpPlayers(),
                new CardDeck(new CardDeckGenerator()));

        outputView.printParticipantInitialCards(blackjackGame.getPlayers(), blackjackGame.getDealer());
        return blackjackGame;
    }

    private Map<String, Integer> setUpPlayers() {
        List<String> playerNames = inputView.requestPlayerNamesToPlayGame();
        return playerNames.stream()
                .collect(Collectors.toMap(playerName -> playerName, this::betMoney));
    }

    private int betMoney(String playerName) {
        return inputView.requestPlayerBettingMoney(playerName);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        turnOfPlayer(blackjackGame);
        turnOfDealer(blackjackGame, blackjackGame.getDealer());
    }

    private void turnOfPlayer(final BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            processForPlayer(blackjackGame, player);
        }
    }

    private void processForPlayer(final BlackjackGame blackjackGame, final Player player) {
        while (player.isRunning() && player.isHit(requestDrawStatus(player.getName()))) {
            blackjackGame.hit(player);
            outputView.printPlayerCardStatus(player.getName(), player.getCards());
        }
        player.stay();
    }

    private DrawStatus requestDrawStatus(final String playerName) {
        try {
            return DrawStatus.from(inputView.requestHitOrStay(playerName));
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return requestDrawStatus(playerName);
        }
    }

    private void turnOfDealer(final BlackjackGame blackjackGame, Dealer dealer) {
        while (dealer.isRangeScoreToReceive()) {
            outputView.printDealerDrawOneMoreCard();
            blackjackGame.hit(dealer);
        }
    }

    private void announceGameResult(final BlackjackGame blackjackGame) {
        ParticipantResult participantResult = blackjackGame.findGameResult();

        outputView.printAllPlayerCardStatus(blackjackGame.getPlayers(), blackjackGame.getDealer());
        outputView.printGameResult(participantResult.getDealerResultCount(), participantResult.getPlayerResults());
    }
}
