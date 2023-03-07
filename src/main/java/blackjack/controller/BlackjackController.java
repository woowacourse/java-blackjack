package blackjack.controller;

import blackjack.domain.card.Deck;
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
        final Deck deck = new Deck();

        startGame(participants, deck);
        hitParticipants(participants, deck);

        final ResultGame resultGame = new ResultGame(participants, new HashMap<>());
        displayAllResult(participants, resultGame);
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new ArrayList<>());
        final List<String> playerNames = inputView.readPlayers();

        return new Participants(dealer, playerNames, new ArrayList<>());
    }

    private void startGame(final Participants participants, final Deck deck) {
        participants.draw(deck, 2);

        outputView.printInitialHandOutMessage(participants);
    }

    private void hitParticipants(final Participants participants, final Deck deck) {
        hitPlayers(participants, deck);
        hitDealer(participants, deck);
        displayParticipantsCardsAndScore(participants);
    }

    private void hitPlayers(final Participants participants, final Deck deck) {
        final List<Participant> players = participants.getPlayers();

        for (final Participant player : players) {
            hitEachPlayer(player, deck);
        }
    }

    private void hitEachPlayer(final Participant player, final Deck deck) {
        while (player.isHit() && isMoreHit(player)) {
            player.drawCard(deck);
            outputView.printParticipantNameAndCards(player);
        }
    }

    private boolean isMoreHit(final Participant player) {
        final Order order = Order.from(inputView.readOrderCard(player.getName()));
        return order.isYES();
    }

    private void hitDealer(final Participants participants, final Deck deck) {
        final Participant dealer = participants.getDealer();

        while (dealer.isHit()) {
            outputView.printDealerDrawCard();
            dealer.drawCard(deck);
        }
    }

    private void displayParticipantsCardsAndScore(final Participants participants) {
        outputView.printAllCardsAndScore(participants);
    }

    private void displayAllResult(final Participants participants, final ResultGame resultGame) {
        outputView.printParticipantsResult(participants, resultGame);
    }
}
