import blackjack.controller.BlackjackController;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.view.InputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        Deck deck = new Deck();
        List<Participant> participants = blackjackController.createParticipants(InputView.inputNames(), deck);
        Dealer dealer = new Dealer(deck.initDistributeCard());
        Players players = new Players(participants, dealer);

    }
}
