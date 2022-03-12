package blackjack;

import blackjack.dto.BlackJackGameDto;
import blackjack.dto.PlayerDto;
import blackjack.model.BlackJackGame;
import blackjack.model.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Application {
    private final static InputView inputView = new InputView();
    private final static ResultView resultView = new ResultView();

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();

        BlackJackGame blackJackGame = initBlackJackGame();

        giveStartingCardsToBlackJackGame(blackJackGame, cardDeck);

        while (blackJackGame.isDrawPossible()) {
            startTurn(blackJackGame, cardDeck);
            nextTurn(blackJackGame);
        }

        blackJackGame.giveCardBy(cardDeck);
        resultView.printDealerAddCardCount(new BlackJackGameDto(blackJackGame));

        resultView.printScoreResultOfGame(new BlackJackGameDto(blackJackGame));

        resultView.printWinningResultOfGame(new BlackJackGameDto(blackJackGame));
    }

    private static void giveStartingCardsToBlackJackGame(BlackJackGame blackJackGame, CardDeck cardDeck) {
        blackJackGame.giveStartingCardsBy(cardDeck);

        BlackJackGameDto gameDto = new BlackJackGameDto(blackJackGame);
        resultView.printStartingCardsInGame(gameDto);
    }

    private static BlackJackGame initBlackJackGame() {
        List<String> names = InputView.inputNames();
        return new BlackJackGame(names);
    }

    private static void startTurn(BlackJackGame blackJackGame, CardDeck cardDeck) {
        if (blackJackGame.isBustOnNowTurn()) {
            resultView.printBust();
            return;
        }
        String drawSign = inputView.inputDrawCardSign(PlayerDto.from(blackJackGame.getCurrentPlayer()));
        if (drawSign.equals("n")) {
            return;
        }
        blackJackGame.drawCardFrom(cardDeck);
        resultView.printDeck(PlayerDto.from(blackJackGame.getCurrentPlayer()));
        startTurn(blackJackGame, cardDeck);
    }

    private static void nextTurn(BlackJackGame blackJackGame) {
        blackJackGame.nextDrawTurn();
    }

}
