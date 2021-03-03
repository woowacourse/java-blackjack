package blackjack;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<String> participantNames = InputView.inputPlayerNames();
        Participants players = Participants.from(participantNames);
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.receiveCards(cardDeck.drawDefaultCards());
        players.receiveDefaultCards(cardDeck);
        OutputView.printDefaultCards(dealer, players);

        for (Participant player : players.toList()) {
            while (player.isAbleToReceiveCard()) {
                String answer = InputView.inputAnswerToAdditionalCardQuestion(player);
                if (answer.equals("y")) {
                    player.receiveCard(cardDeck.draw());
                }
                OutputView.printParticipantCard(player);
                if (answer.equals("n")) {
                    break;
                }
            }
        }

        if (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
            OutputView.printDealerDrawingMessage();
        }

        OutputView.printFinalCardsAndScore(dealer, players);

    }
}
