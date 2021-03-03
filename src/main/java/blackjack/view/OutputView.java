package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FIRST_CARD_INDEX = 0;

    private OutputView() {
    }

    public static void printDefaultCards(Dealer dealer, Participants participants) {
        String participantNames = participants.getParticipantNames()
                                              .stream()
                                              .collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.\n", dealer.getName(), participantNames);
        printDealerCard(dealer);
        printParticipantCard(participants);
    }

    private static void printDealerCard(Dealer dealer) {
        List<Card> dealerCards = dealer.getCards();
        Card firstCardOfDealer = dealerCards.get(FIRST_CARD_INDEX);
        System.out.printf("%s : %s%s\n", dealer.getName(), getCardInformation(firstCardOfDealer));
    }

    private static String getCardInformation(Card card) {
        return card.getSymbol()
                   .getName() + card.getShape()
                                    .getName();
    }

    private static void printParticipantCard(Participants participants) {
        List<Participant> participantGroup = participants.toList();
        for (Participant participant : participantGroup) {
            List<Card> participantCards = participant.getCards();
            String cards = participantCards.stream()
                                           .map(OutputView::getCardInformation)
                                           .collect(Collectors.joining(", "));
            System.out.println(participant.getName() + " : " + cards);
        }
    }
}
