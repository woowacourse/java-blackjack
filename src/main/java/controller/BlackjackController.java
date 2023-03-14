package controller;

import domain.card.Deck;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            Deck deck = Deck.create();
            Participants participants = Participants.of(getPlayersName());

            doGame(deck, participants);

            printResult(participants);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private List<String> getPlayersName() {
        try {
            return InputView.readPlayersName();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return getPlayersName();
        }
    }

    private void doGame(Deck deck, Participants participants) {
        initBetting(participants);

        initParticipantsHand(deck, participants);
        runPlayersTurn(deck, participants);
        runDealerTurn(deck, participants);
    }

    private void initBetting(Participants participants) {
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            betEachPlayer(player);
        }
    }

    private void betEachPlayer(Player player) {
        try {
            player.betPlayer(InputView.readBetMoney(player));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            betEachPlayer(player);
        }
    }

    private void initParticipantsHand(Deck deck, Participants participants) {
        OutputView.printStartMessage(participants.getPlayersName());

        participants.initHand(deck);

        OutputView.printDealerCard(participants.getDealer());
        OutputView.printPlayersCard(participants.getPlayers());
    }

    public void runPlayersTurn(Deck deck, Participants participants) {
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            runPlayerTurn(deck, player);
        }
        InputView.closeScanner();
    }

    private void runPlayerTurn(Deck deck, Player player) {
        while (!player.isBust() && isCommandHit(player)) {
            player.addCard(deck.pollAvailableCard());
            OutputView.printPlayerCard(player);
        }
    }

    private boolean isCommandHit(Player player) {
        try {
            String targetCommand = InputView.readHit(player);
            return HitCommand.HIT == HitCommand.find(targetCommand);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return isCommandHit(player);
        }
    }

    private void runDealerTurn(Deck deck, Participants participants) {
        if (participants.canDealerHit()) {
            participants.playDealerTurn(deck);
            OutputView.printDealerHit();
        }
    }

    private void printResult(Participants participants) {
        OutputView.printAllHands(participants.getDealer(), participants.getPlayers());
        OutputView.printBettingResult(participants.getPlayerBettingResult(), participants.getDealerBettingResult());
    }
}
