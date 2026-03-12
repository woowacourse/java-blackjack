package controller;

import domain.BlackjackGame;
import domain.FinalResult;
import domain.card.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
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
        final List<Participant> playerList = readPlayers();
        final Participants participants = new Participants(playerList);

        return new BlackjackGame(participants);
    }

    // TODO: inputView와 outputView 명확히 분리하기 (inputView에서 출력도 섞이는 중)
    private List<Participant> readPlayers() {
        final List<Name> playerNames = inputView.readPlayers();

        final List<Participant> players = new ArrayList<>();
        for (final Name name : playerNames) {
            players.add(new Participant(name, new Hand()));
        }

        return players;
    }


    private void firstDraw(final BlackjackGame blackjackGame) {
        blackjackGame.initDraw();
        outputView.printInitHandCard(blackjackGame.getParticipants());
    }


    private void hitOrStandPlayer(final BlackjackGame game) {
        for (final Participant player : game.getParticipants().getPlayers()) {
            hitOrStand(game, player);
        }
        outputView.printWhiteLine();
    }

    private void hitOrStand(final BlackjackGame game, final Participant player) {
        while (game.canPlayerDraw(player)) {
            final boolean hit = inputView.readHitOrStand(player.getName());
            outputView.printCurrentHandCard(player);

            if (!hit) {
                break;
            }

            game.hitPlayer(player);
        }
    }

    private void hitOrStandDealer(final BlackjackGame game) {
        while (game.canDealerDraw()) {
            outputView.printDealerAdditionalDraw();
            game.hitDealer();
        }
    }

    private void printResult(final BlackjackGame game) {

        final Participants participants = game.getParticipants();

        outputView.printHandResults(participants);

        final List<FinalResult> results = game.getGameResults();
        outputView.printGameResults(results);
    }
}
