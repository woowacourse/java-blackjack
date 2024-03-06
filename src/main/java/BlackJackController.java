import java.util.List;

public class BlackJackController {
    public void run() {
        List<String> names = InputView.inputParticipantName();
        Participants participants = new Participants(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJack blackJack = new BlackJack(deck, dealer, participants);
        blackJack.beginDealing();
        OutputView.printParticipants(participants);
        OutputView.printDealerCard(dealer);
        for (Participant participant : participants.getValue()) {
            OutputView.printCard(participant);
        }
        for (Participant participant : participants.getValue()) {
            while ("y".equals(InputView.inputDraw(participant.getName()))) {
                participant.receiveCard(deck.draw());
                OutputView.printCard(participant);
                //TODO : 버스트면 자동으로 아웃
            }
        }
        while (dealer.shouldHit()) {
            OutputView.printDealerHit();
            dealer.receiveCard(deck.draw());
        }
    }
}
