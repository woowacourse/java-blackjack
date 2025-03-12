package blackjack.configuration;

import blackjack.domain.card.CardDeckGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ApplicationConfiguration {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CardDeckGenerator cardDeckGenerator = new CardDeckGenerator();

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public CardDeckGenerator getCardDeckGenerator() {
        return cardDeckGenerator;
    }
}
