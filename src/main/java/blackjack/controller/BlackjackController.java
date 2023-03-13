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

        participants.draw(deck, INITIAL_DRAW_CARD_COUNT);
        outputView.printInitialCards(participants);

        drawCardsParticipants(participants, deck);
        outputView.printAllCardsAndScore(participants);

        outputView.printResult(participants, ResultGame.from(new HashMap<>(), participants));
    }

    private Participants makeParticipants() {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final List<String> playerNames = inputView.readPlayers();

        return Participants.of(dealer, playerNames);
    }

    private void drawCardsParticipants(final Participants participants, final Deck deck) {
        drawCardsPlayers(participants.getPlayers(), deck);
        drawCardsDealer(participants.getDealer(), deck);
    }

    private void drawCardsPlayers(final List<Participant> players, final Deck deck) {
        for (final Participant player : players) {
            hitEachPlayer(player, deck);
        }
    }

    private void hitEachPlayer(final Participant player, final Deck deck) {
        while (player.canHit() && isHitOrStay(player)) {
            player.drawCard(deck.draw());
            outputView.printParticipantNameAndCards(player);
        }
    }

    private boolean isHitOrStay(final Participant player) {
        final Order order = Order.from(inputView.readOrderCard(player.getName()));

        return order.isHit();
    }

    private void drawCardsDealer(final Participant dealer, final Deck deck) {
        while (dealer.canHit()) {
            outputView.printDealerDrawCard();
            dealer.drawCard(deck.draw());
        }
    }
}
