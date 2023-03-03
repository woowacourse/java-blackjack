package blackjack.controller;

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

        players.distributeInitialCards(deck);
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());
        final InitialCardDto initialCardDto = new InitialCardDto(
                dealer.getCards()
                        .get(0), players.findPlayerNameToCards());
        outputView.printInitialCards(initialCardDto);

        for (final String playerName : players.getPlayerNames()) {
            drawPlayerCard(playerName, deck, players);

        }
        while (dealer.isDrawable()) {
            dealer.drawCard(deck.popCard());
            outputView.printDealerCardDrawMessage();
        }
        players.calculateResult(dealer);

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


    private void drawPlayerCard(final String playerName, final Deck deck, final Players players) {
        DrawCommand playerInput;
        while (players.isDrawable(playerName)) {
            playerInput = inputView.inputCommand(playerName);

            if (playerInput == DrawCommand.DRAW) {
                players.draw(playerName, deck);
            }

            final PlayerCardDto playerCardDto = new PlayerCardDto(playerName,
                    players.findCardsByPlayerName(playerName));
            outputView.printCardStatusOfPlayer(playerCardDto);
            if (playerInput == DrawCommand.STAY) {
                break;
            }
        }

    }


    private Players createPlayers() {
        return IllegalArgumentExceptionHandler.repeatUntilNoException(
                inputView::inputPlayerNames, Players::from, outputView);
    }
}
