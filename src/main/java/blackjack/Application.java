package blackjack;

import blackjack.dto.PlayerDto;
import blackjack.trumpcard.CardPack;
import blackjack.view.InputView;
import blackjack.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private final static InputView inputView = new InputView();
    private final static ResultView resultView = new ResultView();

    public static void main(String[] args) {
        List<String> names = inputView.inputEntryNames();

        CardPack cardPack = new CardPack();

        BlackJackGame blackJackGame = new BlackJackGame(names);
        blackJackGame.initGame(cardPack);

        Player dealer = blackJackGame.getDealer();
        List<Player> entries = blackJackGame.getEntries();

        PlayerDto dealerDto = new PlayerDto(dealer);
        List<PlayerDto> entriesDtos = entries.stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());

        resultView.printInitGameResult(dealerDto, entriesDtos);

        while (blackJackGame.isDrawPossible()) {
            playDrawTurn(blackJackGame, cardPack);
            blackJackGame.nextDrawTurn();
        }
    }

    private static void playDrawTurn(BlackJackGame blackJackGame, CardPack cardPack) {
        PlayerDto currentEntryDto = new PlayerDto(blackJackGame.getCurrentPlayer());
        if (blackJackGame.isCurrentPlayerBust()) {
            resultView.printBustMessage(currentEntryDto);
            return;
        }
        if (inputView.inputDrawCardSign(currentEntryDto).equals("n")) {
            resultView.printDeck(currentEntryDto);
            return;
        }
        blackJackGame.drawCardFrom(cardPack);
        PlayerDto currentEntryDto2 = new PlayerDto(blackJackGame.getCurrentPlayer());
        resultView.printDeck(currentEntryDto2);
        playDrawTurn(blackJackGame, cardPack);
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
