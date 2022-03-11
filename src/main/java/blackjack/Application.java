package blackjack;

import blackjack.dto.PlayerDto;
import blackjack.trumpcard.CardPack;
import blackjack.view.InputView;

import blackjack.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        List<String> names = inputView.askEntryNames();

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
/*
        resultView.printDeckInitialized(blackJackGame.getEntryNames());
        resultView.printInitializedDecks(blackJackGame.getNames(), blackJackGame.getDecksToString());

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
}
