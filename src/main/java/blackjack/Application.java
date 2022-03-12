package blackjack;

import blackjack.dto.BlackJackGameDto;
import blackjack.dto.PlayerDto;
import blackjack.trumpcard.CardPack;
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

/*

        do {
            blackJackGame.toNextEntry();
            playTurn(inputView, blackJackGame, resultView);
        } while (blackJackGame.hasNextEntry());

        blackJackGame.hitDealer();
        if (blackJackGame.countCardsAddedToDealer() > 0) {
            resultView.printDealerHitCount(blackJackGame.countCardsAddedToDealer());
        }

        resultView.printScores(blackJackGame.getNames(), blackJackGame.getDecksToString(), blackJackGame.getScores());
    }

    private static void playTurn(InputView inputView, BlackJackGame blackJackGame, ResultView resultView) {
        if (blackJackGame.isCurrentEntryBust()) {
            resultView.printBustMessage(blackJackGame.getCurrentEntryName());
            return;
        }
        if (!inputView.askForHit(blackJackGame.getCurrentEntryName())) {
            resultView.printDeck(blackJackGame.getCurrentEntryName(), blackJackGame.getCurrentDeckToString());
            return;
        }
        blackJackGame.hitCurrentEntry();
        resultView.printDeck(blackJackGame.getCurrentEntryName(), blackJackGame.getCurrentDeckToString());
        playTurn(inputView, blackJackGame, resultView);
    }
    */
}
