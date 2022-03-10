package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final int DEALER_BOUNDARY_SCORE = 16;

    public static void printInitialCards(final Dealer dealer, final Participants participants) {
        printDealMessage(dealer, participants);
        printDealerCard(dealer);
        printParticipantsCards(participants);
    }

    private static void printDealMessage(final Dealer dealer, final Participants participants) {
        List<String> names = new ArrayList<>();
        for (Participant participant : participants) {
            names.add(participant.getName());
        }
        System.out.println(dealer.getName() + String.join(DELIMITER, names) + "에게 2장의 나누었습니다.");
    }

    private static void printDealerCard(final Dealer dealer) {
        System.out.println(dealer.getName() + ": " + dealer.getCards().iterator().next());
    }

    private static void printParticipantsCards(final Participants participants) {
        for (Participant participant : participants) {
            printParticipantCards(participant);
        }
    }

    public static void printParticipantCards(final Participant participant) {
        List<String> cards = new ArrayList<>();
        for (Card card : participant.getCards()) {
            cards.add(card.toString());
        }
        System.out.println(participant.getName() + "카드: " + String.join(DELIMITER, cards));
    }

    public static void printDealerGetCardMessage(Dealer dealer) {
        System.out.println(dealer.getName() + "는 " + DEALER_BOUNDARY_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }
}
