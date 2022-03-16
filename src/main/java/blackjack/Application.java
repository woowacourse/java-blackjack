package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.paticipant.Dealer;
import blackjack.domain.paticipant.Players;
import blackjack.dto.ParticipantCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = InputView.inputPlayerNames();
        final Dealer dealer = new Dealer(cardDeck);
        final Players players = new Players(names, InputView::inputPlayerBetMoney, cardDeck);

        OutputView.printParticipantsFirstCards(ParticipantCards.from(dealer), players.players()
                .stream()
                .map(ParticipantCards::from)
                .collect(Collectors.toList()));


    }

    private static void runPlayerTurn(final Players players) {
//        if ()
    }
}
