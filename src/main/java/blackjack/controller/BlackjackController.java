package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.io.GameInputOutput;
import blackjack.domain.user.Nickname;
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
        BlackJackGame blackjackGame = makeBlackjackGame();
        blackjackGame.runGame();
    }

    private BlackJackGame makeBlackjackGame() {
        List<Nickname> nicknames = inputView.readNicknames();
        CardDeck cardDeck = cardDeckGenerator.makeShuffled();
        GameInputOutput gameInputOutput = makeGameInputOutput();
        return new BlackJackGame(nicknames, cardDeck, gameInputOutput);
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
