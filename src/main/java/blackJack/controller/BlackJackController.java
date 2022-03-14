package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        final Participants participants = getParticipants();
        final BlackJackGame blackJackGame = new BlackJackGame(participants);
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);

        final List<Player> players = playersTurn(participants);
        final Dealer dealer = dealerTurn(blackJackGame);
        OutputView.printGameResult(dealer, players);
        OutputView.printWinDrawLoseResult(
                dealer, blackJackGame.calculateDealerResult(), blackJackGame.calculatePlayersResult());
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

    private List<Player> playersTurn(Participants participants) {
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(player);
        }
        return players;
    }

    private void doEachPlayerTurn(Player player) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            player.receiveCard(Deck.getCard());
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

    private Dealer dealerTurn(BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.doDealerGame();
        OutputView.printDealerReceiveCardCount(dealer);
        return dealer;
    }
}
