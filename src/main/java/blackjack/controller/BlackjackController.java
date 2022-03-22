package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.ParticipantProfit;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.generator.RandomCardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.DrawStatus;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

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
        announceProfit(blackjackGame);
    }

    private BlackjackGame setUpBlackjackGame() {
        final BlackjackGame blackjackGame = BlackjackGame.create(requestPlayerNames(),
                new CardDeck(new RandomCardDeckGenerator()));

        betMoney(blackjackGame);

        outputView.printParticipantInitialCards(blackjackGame.getPlayers(), blackjackGame.getDealer());
        return blackjackGame;
    }

    private List<String> requestPlayerNames() {
        return inputView.requestPlayerNamesToPlayGame();
    }

    private void betMoney(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            player.betMoney(requestBettingMoney(player.getName()));
        }
    }

    private int requestBettingMoney(String playerName) {
        return inputView.requestPlayerBettingMoney(playerName);
    }

    private void playGame(final BlackjackGame blackjackGame) {
        turnOfPlayer(blackjackGame);
        turnOfDealer(blackjackGame);
    }

    private void turnOfPlayer(final BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            processForPlayer(blackjackGame, player);
        }
    }

    private void processForPlayer(final BlackjackGame blackjackGame, final Player player) {
        while (!player.isFinish()) {
            blackjackGame.hitOrStayByPlayer(player, requestDrawStatus(player.getName()));
            outputView.printPlayerCardStatus(player.getName(), player.getCards());
        }
    }

    private DrawStatus requestDrawStatus(final String playerName) {
        try {
            return DrawStatus.from(inputView.requestHitOrStay(playerName));
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return requestDrawStatus(playerName);
        }
    }

    private void turnOfDealer(final BlackjackGame blackjackGame) {
        final Dealer dealer = blackjackGame.getDealer();

        while (dealer.isRangeScoreToReceive()) {
            outputView.printDealerDrawOneMoreCard();
            blackjackGame.hitByDealer();
        }
    }

    private void announceProfit(final BlackjackGame blackjackGame) {
        ParticipantProfit participantProfit = blackjackGame.findProfit();

        outputView.printAllPlayerCardStatus(blackjackGame.getPlayers(), blackjackGame.getDealer());
        outputView.printGameResult(participantProfit.getDealerProfit(), participantProfit.getPlayerProfit());
    }
}
