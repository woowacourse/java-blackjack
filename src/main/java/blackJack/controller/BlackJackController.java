package blackJack.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import blackJack.domain.BlackJackGame;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

public class BlackJackController {

    public void run() {
        Participants participants = getParticipants();
        BlackJackGame blackJackGame = new BlackJackGame(participants);
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(blackJackGame.getDealer(), blackJackGame.getPlayers());
        doPlayerGame(blackJackGame);
        doDealerGame(blackJackGame);
        OutputView.printDealerReceiveCardCount(blackJackGame.getDealer());
        OutputView.printGameResult(blackJackGame.getDealer(), blackJackGame.getPlayers());
        OutputView.printWinOrLoseResult(blackJackGame.getDealer(),
            BlackJackGameResult.ofGameResult(blackJackGame.getDealer(), blackJackGame.getPlayers()));
    }

    private Participants getParticipants() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
            return new Participants(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private void doPlayerGame(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(blackJackGame, player);
        }
    }

    private void doEachPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            blackJackGame.distributeCard(player, 1);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private boolean getOneMoreCard(Player player) {
        try {
            String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getOneMoreCard(player);
        }
    }

    private void doDealerGame(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.hasNextTurn()) {
            blackJackGame.distributeCard(dealer, 1);
        }
    }
}
