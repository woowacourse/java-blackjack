package blackjack.game;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class ConsoleGame {

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();

        Participants participants = blackjackGame.getParticipants();
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        OutputView.printInitialCards(dealer, players);

        playPlayersTurn(blackjackGame, players);
        playDealerTurn(blackjackGame, dealer);

        showGameResult(blackjackGame, dealer, players);
    }

    private BlackjackGame createBlackjackGame() {
        try {
            List<Name> playerNames = InputView.inputPlayerNames();
            List<BettingAmount> bettingAmounts = getBettingAmounts(playerNames);
            return new BlackjackGame(playerNames, bettingAmounts);

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return createBlackjackGame();
        }
    }

    private List<BettingAmount> getBettingAmounts(List<Name> names) {
        List<BettingAmount> bettingAmounts = new ArrayList<>();
        for (Name name : names) {
            bettingAmounts.add(inputBettingAmounts(name));
        }
        return bettingAmounts;
    }

    private BettingAmount inputBettingAmounts(Name name) {
        try {
            return InputView.inputBettingAmount(name);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputBettingAmounts(name);
        }
    }

    private void playPlayersTurn(BlackjackGame blackjackGame, List<Player> players) {
        for (Player player : players) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (!player.isFinished() && !inputCommand(player).isStay()) {
            blackjackGame.drawCard(player);
            OutputView.printCards(player);
        }
    }

    private Command inputCommand(Participant player) {
        try {
            return InputView.inputWantDraw(player.getName());

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputCommand(player);
        }
    }

    private void playDealerTurn(BlackjackGame blackjackGame, Dealer dealer) {
        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo();
            blackjackGame.drawCard(dealer);
        }
    }

    private void showGameResult(BlackjackGame blackjackGame, Dealer dealer, List<Player> players) {
        GameResult gameResult = blackjackGame.createGameResult();
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }
}
