package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.value.Nickname;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardDeckGenerator cardDeckGenerator;

    public BlackjackController() {
        inputView = new InputView();
        outputView = new OutputView();
        cardDeckGenerator = new CardDeckGenerator();
    }

    public void run() {
        List<Nickname> nicknames = inputView.readNicknames();
        BlackJackGame blackjackGame = makeBlackjackGame();
        blackjackGame.processPreparation(nicknames);
        blackjackGame.processPlayerTurns();
        blackjackGame.processDealerTurns();
        blackjackGame.processOutputResult();
    }

    private BlackJackGame makeBlackjackGame() {
        GameUserStorage gameUserStorage = new GameUserStorage();
        CardDeck cardDeck = cardDeckGenerator.makeShuffled();
        GameInputOutput gameInputOutput = makeGameInputOutput();
        return new BlackJackGame(gameUserStorage, cardDeck, gameInputOutput);
    }

    private GameInputOutput makeGameInputOutput() {
        return new GameInputOutput(
                outputView::printInitialHands,
                inputView::readWannaHit,
                inputView::readBettingAmount,
                outputView::printHitResult,
                outputView::printDealerDrawing,
                outputView::printFinalHands,
                outputView::printProfit);
    }
}
