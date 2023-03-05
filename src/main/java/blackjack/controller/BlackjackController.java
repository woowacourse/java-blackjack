package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.cardPicker.CardPicker;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Order;
import blackjack.domain.game.ResultGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardPicker cardPicker;

    public BlackjackController(final InputView inputView, final OutputView outputView, final CardPicker cardPicker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardPicker = cardPicker;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final Deck deck = makeDeck();
        final BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        startGame(blackjackGame);
        hitParticipants(blackjackGame);

        final ResultGame resultGame = new ResultGame(participants);
        showAllResult(participants, resultGame);
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer();
        final List<String> playerNames = inputView.readPlayers();
        return new Participants(dealer, playerNames);
    }

    private Deck makeDeck() {
        final DeckMaker deckMaker = new DeckMaker();
        return new Deck(deckMaker.makeDeck(), cardPicker);
    }

    private void startGame(final BlackjackGame blackjackGame) {
        final Participants participants = blackjackGame.getParticipants();

        blackjackGame.giveTwoCardEveryone();

        outputView.printHandOutStart(participants);
    }

    private void hitParticipants(final BlackjackGame blackjackGame) {
        hitPlayers(blackjackGame);
        hitDealer(blackjackGame);
        showAllCardsAndScore(blackjackGame);
    }

    private void hitPlayers(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getParticipants().getPlayers();

        for (final Player player : players) {
            hitEachPlayer(blackjackGame, player);
        }
    }

    private void hitEachPlayer(final BlackjackGame blackjackGame, final Player player) {
        while (!player.isBust() && isMoreHit(player)) {
            blackjackGame.drawCard(player);
            outputView.printParticipantCard(player);
        }
    }

    private boolean isMoreHit(final Player player) {
        final Order order = Order.from(inputView.readOrderCard(player.getName()));
        return order.isYES();
    }

    private void hitDealer(final BlackjackGame blackjackGame) {
        final Dealer dealer = blackjackGame.getParticipants().getDealer();

        while (dealer.canHit()) {
            outputView.printDealerDrawCard(dealer);
            blackjackGame.drawCard(dealer);
        }
    }

    private void showAllCardsAndScore(final BlackjackGame blackjackGame) {
        final Participants participants = blackjackGame.getParticipants();

        outputView.printAllCardsAndScores(participants);
    }

    private void showAllResult(Participants participants, ResultGame resultGame) {
        outputView.printResult(participants, resultGame);
    }
}
