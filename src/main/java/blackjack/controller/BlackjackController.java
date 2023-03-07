package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Order;
import blackjack.domain.game.ResultGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(participants, new Deck());

        startGame(blackjackGame);
        hitParticipants(blackjackGame);

        final ResultGame resultGame = new ResultGame(participants, new HashMap<>());
        displayAllResult(participants, resultGame);
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new ArrayList<>());
        final List<String> playerNames = inputView.readPlayers();

        return new Participants(dealer, playerNames, new ArrayList<>());
    }

    private void startGame(final BlackjackGame blackjackGame) {
        blackjackGame.initialCardsToAllParticipant();

        final Participants participants = blackjackGame.getParticipants();
        outputView.printInitialHandOutMessage(participants);
    }

    private void hitParticipants(final BlackjackGame blackjackGame) {
        hitPlayers(blackjackGame);
        hitDealer(blackjackGame);
        displayParticipantsCardsAndScore(blackjackGame);
    }

    private void hitPlayers(final BlackjackGame blackjackGame) {
        final List<Participant> players = blackjackGame.getParticipants().getPlayers();

        for (final Participant player : players) {
            hitEachPlayer(blackjackGame, player);
        }
    }

    private void hitEachPlayer(final BlackjackGame blackjackGame, final Participant player) {
        while (player.isHit() && isMoreHit(player)) {
            blackjackGame.drawCard(player);
            outputView.printParticipantNameAndCards(player);
        }
    }

    private boolean isMoreHit(final Participant player) {
        final Order order = Order.from(inputView.readOrderCard(player.getName()));
        return order.isYES();
    }

    private void hitDealer(final BlackjackGame blackjackGame) {
        final Participant dealer = blackjackGame.getParticipants().getDealer();

        while (dealer.isHit()) {
            outputView.printDealerDrawCard();
            blackjackGame.drawCard(dealer);
        }
    }

    private void displayParticipantsCardsAndScore(final BlackjackGame blackjackGame) {
        final Participants participants = blackjackGame.getParticipants();

        outputView.printAllCardsAndScore(participants);
    }

    private void displayAllResult(final Participants participants, final ResultGame resultGame) {
        outputView.printParticipantsResult(participants, resultGame);
    }
}
