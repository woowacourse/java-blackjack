package blackjack;

import blackjack.dto.BlackJackGameDto;
import blackjack.dto.PlayerDto;
import blackjack.model.BlackJackGame;
import blackjack.model.card.CardPack;
import blackjack.view.InputView;
import blackjack.view.ResultView;

import java.util.List;

public class Application {
    private final static InputView inputView = new InputView();
    private final static ResultView resultView = new ResultView();

    public static void main(String[] args) {
        List<String> names = inputView.inputEntryNames();

        CardPack cardPack = new CardPack();

        BlackJackGame blackJackGame = new BlackJackGame(names);
        blackJackGame.giveStartingCardsBy(cardPack);

        BlackJackGameDto gameDto = new BlackJackGameDto(blackJackGame);
        resultView.printStartingCardsInGame(gameDto);

        while (blackJackGame.isDrawPossible()) {
            startTurn(blackJackGame, cardPack);
            nextTurn(blackJackGame);
        }

        blackJackGame.giveCardBy(cardPack);
        resultView.printDealerAddCardCount(new BlackJackGameDto(blackJackGame));

        resultView.printScoreResultOfGame(new BlackJackGameDto(blackJackGame));

        resultView.printWinningResultOfGame(new BlackJackGameDto(blackJackGame));
    }

    private static void startTurn(BlackJackGame blackJackGame, CardPack cardPack) {
        if (blackJackGame.isBustOnNowTurn()) {
            resultView.printBust();
            return;
        }
        String drawSign = inputView.inputDrawCardSign(PlayerDto.from(blackJackGame.getCurrentPlayer()));
        if (drawSign.equals("n")) {
            return;
        }
        blackJackGame.drawCardFrom(cardPack);
        resultView.printDeck(PlayerDto.from(blackJackGame.getCurrentPlayer()));
        startTurn(blackJackGame, cardPack);
    }

    private static void nextTurn(BlackJackGame blackJackGame) {
        blackJackGame.nextDrawTurn();
    }

}
