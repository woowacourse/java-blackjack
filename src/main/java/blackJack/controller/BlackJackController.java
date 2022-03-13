package blackJack.controller;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int ADDITIONAL_CARD_COUNT = 1;

    public void run() {
        Participants participants = getParticipants();
        participants.firstCardDispensing();
        OutputView.printInitCardResult(participants);

        doPlayerGame(participants);
        doDealerGame(participants);
        OutputView.printGameResult(participants);
        OutputView.printWinDrawLoseResult(participants.getDealer(),
                BlackJackGameResult.ofGameResult(participants.getDealer(), participants.getPlayers()));
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

    private void doPlayerGame(Participants participants) {
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(participants, player);
        }
    }

    private void doEachPlayerTurn(Participants participants, Player player) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            participants.distributeCard(player, ADDITIONAL_CARD_COUNT);
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

    private void doDealerGame(Participants participants) {
        Dealer dealer = participants.getDealer();
        while (dealer.hasNextTurn()) {
            participants.distributeCard(dealer, ADDITIONAL_CARD_COUNT);
        }
        OutputView.printDealerReceiveCardCount(participants.getDealer());
    }
}
