package blackjack.controller;

import static blackjack.controller.Repeater.repeatUntilNoException;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.DeckFactory;
import blackjack.domain.Players;
import blackjack.dto.CardsScoreDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.InitialCardDto;
import blackjack.dto.PlayerCardDto;
import blackjack.dto.PlayerCardsScoreDto;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

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
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());
    }


    private void printInitialCards(final Players players, final Dealer dealer) {
        final InitialCardDto initialCardDto = new InitialCardDto(
                dealer.getCards()
                        .get(0),
                players.findPlayerNameToCards());
        outputView.printInitialCards(initialCardDto);
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
        final PlayerCardDto playerCardDto = new PlayerCardDto(playerName,
                players.findCardsByPlayerName(playerName));
        outputView.printCardStatusOfPlayer(playerCardDto);
    }


    private void drawDealerCards(final Dealer dealer, final Deck deck) {
        while (dealer.isDrawable()) {
            dealer.drawCard(deck.popCard());
            outputView.printDealerCardDrawMessage();
        }
    }

    private void printResult(final Players players, final Dealer dealer) {
        printStatusOfGame(dealer, players);
        outputView.printFinalResult(new FinalResultDto(dealer.getResult()));
    }

    private void printStatusOfGame(final Dealer dealer, final Players players) {
        outputView.printFinalStatusOfDealer(
                new CardsScoreDto(dealer.getCards(), dealer.currentScore()));
        outputView.printFinalStatusOfPlayers(createPlayerCardDto(players));
    }

    private PlayerCardsScoreDto createPlayerCardDto(final Players players) {
        final Map<String, CardsScoreDto> playerNameToResult = new LinkedHashMap<>();

        for (final String playerName : players.getPlayerNames()) {
            final CardsScoreDto playerCardDto = new CardsScoreDto(
                    players.findCardsByPlayerName(playerName),
                    players.getPlayerScoreByName(playerName)
            );
            playerNameToResult.put(playerName, playerCardDto);
        }

        return new PlayerCardsScoreDto(playerNameToResult);
    }
}
