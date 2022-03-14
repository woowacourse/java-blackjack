package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.DrawStatus;
import blackjack.domain.ParticipantResult;
import blackjack.domain.player.Dealer;
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
        announceGameResult(blackjackGame);
    }

    private BlackjackGame setUpBlackjackGame() {
        final BlackjackGame blackjackGame = BlackjackGame.create(inputView.requestPlayerNamesToPlayGame());

        outputView.printParticipantInitialCards(blackjackGame.getPlayers(), blackjackGame.getDealer());
        return blackjackGame;
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
        while (player.isPossibleToReceiveCard() && player.isHit(requestDrawStatus(player.getName()))) {
            blackjackGame.drawCardToParticipant(player);
            outputView.printPlayerCardStatus(player.getName(), player.getCards().getCards());
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

    private void turnOfDealer(final BlackjackGame blackjackGame, Dealer dealer) {
        while (dealer.isPossibleToReceiveCard()) {
            outputView.printDealerDrawOneMoreCard();
            blackjackGame.drawCardToParticipant(dealer);
        }
    }

    private void announceGameResult(BlackjackGame blackjackGame) {
        final Dealer dealer = blackjackGame.getDealer();
        final List<Player> players = blackjackGame.getPlayers();

        ParticipantResult participantResult = ParticipantResult.create(dealer.calculateScore(),
                players);
        outputView.printAllPlayerCardStatus(players, dealer);
        outputView.printGameResult(participantResult.getDealerResultCount(), participantResult.getPlayerResults());
    }
}
