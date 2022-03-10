package blackjack.controller;

import blackjack.domain.BlackJackMachine;
import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());
        List<String> names = InputView.getParticipantNames();
        Dealer dealer = new Dealer();
        Participants participants = new Participants(names);
        blackJackMachine.giveInitialCards(dealer, participants);

        OutputView.printInitialCards(dealer, participants);

    }
}
