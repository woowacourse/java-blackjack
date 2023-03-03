package blackjack.view;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import java.util.List;

public class OutputView {

    public static final int FIRST_HIT_COUNT = 2;
    public static final int FIRST_CARD = 0;

    public void printParticipants(List<String> participants) {
        StringBuilder stringBuilder = new StringBuilder(participants.remove(FIRST_CARD));
        stringBuilder.append("와 ");
        stringBuilder.append(String.join(", ", participants));
        stringBuilder.append("에게 2장을 나누었습니다.");
        System.out.println(stringBuilder);
    }

    public void printParticipantsCard(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            printFirstDealerCards(participant);
            printFirstPlayersCards(participant);
        }
    }

    private void printFirstDealerCards(final Participant participant) {
        if (participant.isDealer()) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(FIRST_CARD));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(FIRST_CARD));
            System.out.println(participant.getName()+ " : " +cardNumber.getName() +cardSuit.getCardSuitName());
        }
    }

    private void printFirstPlayersCards(final Participant participant) {
        for(int i = 0; i < FIRST_HIT_COUNT; i++) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(i));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(i));
            System.out.println(participant.getName()+ " : " +cardNumber.getName() +cardSuit.getCardSuitName());
        }
    }
}
