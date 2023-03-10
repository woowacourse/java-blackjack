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

public final class BlackjackController {

    private static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final Deck deck = Deck.createTrump();

        startGame(participants, deck);
        hitParticipants(participants, deck);
        displayAllResult(participants);
    }

    private Participants makeParticipants() {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final List<String> playerNames = inputView.readPlayers();

        return Participants.of(dealer, playerNames);
    }

    private void startGame(final Participants participants, final Deck deck) {
        participants.draw(deck, INITIAL_DRAW_CARD_COUNT);

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
            player.drawCard(deck.draw());
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
            dealer.drawCard(deck.draw());
        }
    }

    private void displayParticipantsCardsAndScore(final Participants participants) {
        outputView.printAllCardsAndScore(participants);
    }

    private void displayAllResult(final Participants participants) {
        final ResultGame resultGame = ResultGame.from(new HashMap<>(), participants);

        outputView.printParticipantsResult(participants, resultGame);
    }
}
