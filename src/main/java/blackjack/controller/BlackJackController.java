package blackjack.controller;

import blackjack.domain.BlackJackGame;
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
        final BlackJackGame blackJackGame = new BlackJackGame(players);
        final Deck deck = deckFactory.generate();

        blackJackGame.distributeInitialCards(deck);
        final InitialCardDto initialCardDto = new InitialCardDto(
                blackJackGame.findDealerInitialCard(), blackJackGame.findPlayerNameToCards());
        outputView.printInitialCards(initialCardDto);

        //플레이어 카드 드로우
        for (final String playerName : blackJackGame.getPlayerNames()) {
            drawPlayerCard(playerName, deck, blackJackGame);

        }
        //
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.dealerDrawCard(deck);
            outputView.printDealerCardDrawMessage();
        }
        blackJackGame.calculateResult();

        printStatusOfGame(blackJackGame);
        outputView.printFinalResult(new FinalResultDto(blackJackGame.getDealersResult()));
    }

    private void printStatusOfGame(final BlackJackGame blackJackGame) {
        outputView.printFinalStatusOfDealer(new CardsScoreDto(blackJackGame.getDealerCards(), blackJackGame.getDealerScore()));
        outputView.printFinalStatusOfPlayers(createPlayerCardDto(blackJackGame));
    }

    private PlayerCardsScoreDto createPlayerCardDto(final BlackJackGame blackJackGame) {
        final Map<String, CardsScoreDto> playerNameToResult = new LinkedHashMap<>();

        for (final String playerName : blackJackGame.getPlayerNames()) {
            final CardsScoreDto playerCardDto = new CardsScoreDto(
                    blackJackGame.findCardsByPlayerName(playerName)
                            .get(),
                    blackJackGame.getPlayerScoreByName(playerName)
            );

            playerNameToResult.put(playerName, playerCardDto);
        }

        return new PlayerCardsScoreDto(playerNameToResult);
    }


    private void drawPlayerCard(final String playerName, final Deck deck, final BlackJackGame blackJackGame) {
        DrawCommand playerInput;
        while (blackJackGame.isPlayerDrawable(playerName)) {
            playerInput = inputView.inputCommand(playerName);

            if (playerInput == DrawCommand.DRAW) {
                blackJackGame.drawPlayerCard(playerName, deck);
            }

            final PlayerCardDto playerCardDto = new PlayerCardDto(playerName, blackJackGame.findCardsByPlayerName(playerName)
                    .get());
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
