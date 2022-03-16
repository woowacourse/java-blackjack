package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.paticipant.Players;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = InputView.inputPlayerNames();
        final Players players = new Players(names, InputView::inputPlayerBetMoney, cardDeck);

    }
}
