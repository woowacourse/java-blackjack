package blackjack.view;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.WinningResult;
import java.util.List;
import java.util.Map;

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

    public void printCurrentCards(Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        for (int index = 0, end = participant.getReceivedCards().size(); index < end; index++) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            System.out.print(cardNumber.getName() + cardSuit.getCardSuitName());
            System.out.println();
        }
    }

    public void printTotalCardsAndScore(Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        for (int index = 0, end = participant.getReceivedCards().size(); index < end; index++) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            System.out.print(cardNumber.getName() + cardSuit.getCardSuitName());
        }
        System.out.print("- 결과 : " + participant.calculateCardNumber());
        System.out.println();
    }

    public void dealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerWinORLose(List<WinningResult> dealerResult) {
        System.out.print("딜러: ");
        System.out.print(ViewWinningResult.WIN.winCount(dealerResult) + ViewWinningResult.WIN.getName());
        System.out.println(ViewWinningResult.PUSH.pushCount(dealerResult) + ViewWinningResult.PUSH.getName());
        System.out.println(ViewWinningResult.LOSE.loseCount(dealerResult) + ViewWinningResult.LOSE.getName());
    }

    public void printPlayerWinORLose(Map<Participant, WinningResult> playerResult) {
        for (Participant player : playerResult.keySet()) {
            System.out.println(player.getName() + " : " + playerResult.get(player));
        }
    }

    private void printFirstDealerCards(final Participant participant) {
        if (participant.isDealer()) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(FIRST_CARD));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(FIRST_CARD));
            System.out.println(participant.getName() + " : " + cardNumber.getName() + cardSuit.getCardSuitName());
        }
    }

    private void printFirstPlayersCards(final Participant participant) {
        for (int i = 0; i < FIRST_HIT_COUNT; i++) {
            ViewCardNumber cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(i));
            ViewCardSuit cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(i));
            System.out.println(participant.getName() + " : " + cardNumber.getName() + cardSuit.getCardSuitName());
        }
    }
}
