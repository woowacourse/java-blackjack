package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.Betting;
import blackjack.domain.game.BettingTable;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Order;
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
        final Deck deck = Deck.create();
        final BettingTable table = BettingTable.from(new HashMap<>());
        initialBettingMoney(participants, table);

        startDrawParticipants(participants, deck);
        drawParticipants(participants, deck);

        final GameResult resultGame = GameResult.of(table, participants);
        outputView.printBettingResult(participants, resultGame);
    }

    private void drawParticipants(final Participants participants, final Deck deck) {
        drawCardsForAllParticipants(participants, deck);
        outputView.printCardsAndScoreForAllParticipants(participants);
    }

    private Participants makeParticipants() {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final List<String> playerNames = inputView.readPlayers();

        return Participants.of(dealer, playerNames);
    }

    private void initialBettingMoney(final Participants participants, final BettingTable bettingTable) {
        for (final Participant player : participants.getPlayers()) {
            final Betting betting = Betting.from(inputView.readBettingMoney(player.getName()));
            bettingTable.addBetting(player, betting);
        }
    }

    private void startDrawParticipants(final Participants participants, final Deck deck) {
        participants.draw(deck, INITIAL_DRAW_CARD_COUNT);
        outputView.printInitialCards(participants);
    }

    private void drawCardsForAllParticipants(final Participants participants, final Deck deck) {
        drawCardsForAllPlayers(participants.getPlayers(), deck);
        drawCardsForDealer(participants.getDealer(), deck);
    }

    private void drawCardsForAllPlayers(final List<Participant> players, final Deck deck) {
        for (final Participant player : players) {
            handlePlayerCardDraws(player, deck);
        }
    }

    private void handlePlayerCardDraws(final Participant player, final Deck deck) {
        while (player.canHit() && isHitOrStay(player)) {
            player.drawCard(deck.draw());
            outputView.printParticipantNameAndCards(player);
        }
    }

    private boolean isHitOrStay(final Participant player) {
        final Order order = Order.from(inputView.readOrderCard(player.getName()));

        return order.isHit();
    }

    private void drawCardsForDealer(final Participant dealer, final Deck deck) {
        while (dealer.canHit()) {
            outputView.printDealerDrawAdditionalCard();
            dealer.drawCard(deck.draw());
        }
    }
}
