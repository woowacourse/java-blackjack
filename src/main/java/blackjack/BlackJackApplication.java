package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.Name;
import blackjack.domain.card.BlackJackCardsGenerator;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());
        Dealer dealer = new Dealer(deck.drawDouble());
        List<Name> playerNames = inputPlayerNames();
    }


    private static List<Name> inputPlayerNames() {
        try {
            return InputView.inputPlayerNames().stream()
                    .map(Name::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }
}
