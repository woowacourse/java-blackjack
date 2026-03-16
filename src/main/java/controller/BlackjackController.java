package controller;

import domain.BlackjackGame;
import domain.card.Hand;
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
        final Participants participants = generateParticipants();

        for (final Player player : participants.getPlayers()) {
            outputView.printBetAmountRequest(player.getName());
            inputView.readBetAmount();
        }

        return new BlackjackGame(participants);
    }

    private Participants generateParticipants() {
        while (true) {
            try {
                final List<Player> playerList = readPlayers();
                return new Participants(playerList);
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    // TODO: inputView와 outputView 명확히 분리하기 (inputView에서 출력도 섞이는 중)
    private List<Player> readPlayers() {
        final List<Name> playerNames = inputView.readPlayers();

        final List<Player> players = new ArrayList<>();
        for (final Name name : playerNames) {
            players.add(new Player(name, new Hand()));
        }

        return players;
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
        while (player.canDraw()) {
            final boolean hit = inputView.readHitOrStand(player.getName());

            if (!hit) {
                outputView.printCurrentHandCard(player);
                break;
            }

            game.hit(player);
            outputView.printCurrentHandCard(player);
        }
    }

    private void hitOrStandDealer(final BlackjackGame game) {
        while (game.getParticipants().getDealer().shouldDraw()) {
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
