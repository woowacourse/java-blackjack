package controller;

import domain.BlackjackGame;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.GameResults;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initializeGame();

        firstDraw(blackjackGame);

        hitOrStandPlayer(blackjackGame);
        hitOrStandDealer(blackjackGame);

        printResult(blackjackGame);
    }


    private BlackjackGame initializeGame() {
        while (true) {
            try {
                final List<Name> playerNames = readPlayerNames();

                final List<Player> players = generatePlayersWithBetAmount(playerNames);

                final Participants participants = new Participants(players);

                return new BlackjackGame(participants);
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Player> generatePlayersWithBetAmount(final List<Name> playerNames) {
        final List<Player> players = new ArrayList<>();

        for (final Name playerName : playerNames) {
            outputView.printBetAmountRequest(playerName.name());
            final int betAmount = inputView.readBetAmount();
            players.add(new Player(playerName, betAmount));
        }

        return players;
    }

    private List<Name> readPlayerNames() {
        while (true) {
            try {
                outputView.printPlayerNamesRequest();
                return inputView.readPlayers();
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }


    private void firstDraw(final BlackjackGame blackjackGame) {
        blackjackGame.initDraw();
        outputView.printInitHandCard(blackjackGame.getParticipants());
    }


    private void hitOrStandPlayer(final BlackjackGame game) {
        for (final Player player : game.getParticipants().getPlayers()) {
            hitOrStand(game, player);
        }
        outputView.printWhiteLine();
    }

    private void hitOrStand(final BlackjackGame game, final Player player) {
        while (player.isDrawable()) {
            final boolean hit = readHitOrStand(player);

            if (!hit) {
                outputView.printCurrentHandCard(player);
                break;
            }

            game.hit(player);
            outputView.printCurrentHandCard(player);
        }
    }

    private boolean readHitOrStand(final Player player) {
        while (true) {
            try {
                outputView.printHitOrStandRequest(player.getName());
                return inputView.readHitOrStand();
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void hitOrStandDealer(final BlackjackGame game) {
        while (game.getParticipants().getDealer().isDrawable()) {
            outputView.printDealerAdditionalDraw();
            game.hit(game.getParticipants().getDealer());
        }
    }

    private void printResult(final BlackjackGame game) {
        final Participants participants = game.getParticipants();

        outputView.printHandResults(participants);

        final GameResults results = game.getGameResults();
        outputView.printGameResults(results);
    }
}
