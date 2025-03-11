package blackjack.configuration;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardGenerator;
import blackjack.domain.game.GameManager;
import blackjack.domain.user.GameUserStorage;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ApplicationConfiguration {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final GameUserStorage gameUserStorage = new GameUserStorage();
    private final CardGenerator cardGenerator = new CardGenerator();
    private final CardDeck cardDeck = new CardDeck(cardGenerator);
    private final GameManager gameManager = new GameManager(gameUserStorage, cardDeck);

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
