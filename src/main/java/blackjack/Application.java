package blackjack;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<String> participantNames = InputView.inputPlayerNames();
        Participants players = Participants.from(participantNames);
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.receiveDefaultCards(cardDeck.drawDefaultCards());
        players.receiveDefaultCards(cardDeck);

    }
}
