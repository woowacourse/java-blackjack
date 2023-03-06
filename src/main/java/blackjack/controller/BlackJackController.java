package blackjack.controller;

import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.DeckFactory;
import blackjack.domain.Players;
import blackjack.response.CardsScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play(final DeckFactory deckFactory) {
        final Players players = createPlayers();
        final Dealer dealer = new Dealer();
        final Deck deck = deckFactory.generate();

        distributeInitialCard(players, dealer, deck);
        printInitialCards(players, dealer);
        drawPlayersCards(players, deck);
        drawDealerCards(dealer, deck);
        players.calculateResult(dealer);
        printResult(players, dealer);
    }

    private Players createPlayers() {
        return repeatUntilNoException(
                () -> Players.from(inputView.inputPlayerNames()), outputView::printError);
    }

    private void distributeInitialCard(final Players players, final Dealer dealer, final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawCard(deck.removeCard());
        dealer.drawCard(deck.removeCard());
    }


    private void printInitialCards(final Players players, final Dealer dealer) {
        final InitialCardResponse initialCardResponse = InitialCardResponse.of(players, dealer);
        outputView.printInitialCards(initialCardResponse);
    }

    private void drawPlayersCards(final Players players, final Deck deck) {
        for (final String playerName : players.getPlayerNames()) {
            drawPlayerCard(playerName, deck, players);
        }
    }

    private void drawPlayerCard(final String playerName, final Deck deck, final Players players) {
        DrawCommand playerInput = DrawCommand.DRAW;
        while (players.isDrawable(playerName) && playerInput != DrawCommand.STAY) {
            playerInput = repeatUntilNoException(
                    () -> inputView.inputCommand(playerName), outputView::printError);
            drawCard(playerName, deck, players, playerInput);
            printPlayerResult(playerName, players);
        }
    }

    private void drawCard(final String playerName, final Deck deck, final Players players,
            final DrawCommand playerInput) {
        if (playerInput == DrawCommand.DRAW) {
            players.draw(playerName, deck);
        }
    }

    private void printPlayerResult(final String playerName, final Players players) {
        final PlayerCardsResponse playerCardsResponse = PlayerCardsResponse.of(
                playerName,
                players.findPlayerByName(playerName)
        );
        outputView.printCardStatusOfPlayer(playerCardsResponse);
    }

    private void drawDealerCards(final Dealer dealer, final Deck deck) {
        while (dealer.isDrawable()) {
            dealer.drawCard(deck.removeCard());
            outputView.printDealerCardDrawMessage();
        }
    }

    private void printResult(final Players players, final Dealer dealer) {
        printStatusOfGame(dealer, players);
        outputView.printFinalResult(FinalResultResponse.from(dealer.getResult()));
    }

    private void printStatusOfGame(final Dealer dealer, final Players players) {
        outputView.printFinalStatusOfDealer(CardsScoreResponse.from(dealer));
        outputView.printFinalStatusOfPlayers(createPlayersCardsResponse(players));
    }

    private PlayersCardsResponse createPlayersCardsResponse(final Players players) {
        return PlayersCardsResponse.from(players);
    }
}
