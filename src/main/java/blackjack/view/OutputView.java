package blackjack.view;

import blackjack.domain.betting.Revenue;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final int FIRST_HIT_COUNT = 2;
    private static final int FIRST_CARD = 0;
    private static final int DEALER_INDEX = 0;

    public void printParticipants(List<String> participants) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder(participants.remove(DEALER_INDEX));
        stringBuilder.append("와 ");
        stringBuilder.append(String.join(", ", participants));
        stringBuilder.append("에게 2장을 나누었습니다.");
        System.out.println(stringBuilder);
    }

    public void printParticipantsCard(Participants participants) {
        printFirstDealerCards(participants.getParticipants().get(DEALER_INDEX));
        for (Player player : participants.extractPlayers()) {
            printFirstPlayersCards(player);
        }
    }

    public void printCurrentCards(Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getHand().getReceivedCards().size(); index < end; index++) {
            ViewCardNumber cardNumber = ViewCardNumber.findCardNumber(participant.getCardNumber(index));
            ViewCardSuit cardSuit = ViewCardSuit.findCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber.getName() + cardSuit.getCardSuitName());
        }
        System.out.print(String.join(", ", cards));
        System.out.println();
    }

    public void printTotalCardsAndScore(Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getHand().getReceivedCards().size(); index < end; index++) {
            ViewCardNumber cardNumber = ViewCardNumber.findCardNumber(participant.getCardNumber(index));
            ViewCardSuit cardSuit = ViewCardSuit.findCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber.getName() + cardSuit.getCardSuitName());
        }
        System.out.print(String.join(", ", cards));
        System.out.print("- 결과 : " + participant.calculateCardNumber());
        System.out.println();
    }

    public void dealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerRevenue(int dealerRevenue) {
        System.out.println("딜러: "+ dealerRevenue);
    }

    public void printPlayerRevenue(Map<Player, Revenue> playerRevenue) {
        for (Participant player : playerRevenue.keySet()) {
            System.out.println(player.getName() + " : " + playerRevenue.get(player).getRevenue());
        }
    }

    private void printFirstDealerCards(final Participant participant) {
        if (participant.isDealer()) {
            ViewCardNumber cardNumber = ViewCardNumber.findCardNumber(participant.getCardNumber(FIRST_CARD));
            ViewCardSuit cardSuit = ViewCardSuit.findCardSuit(participant.getCardSuit(FIRST_CARD));
            System.out.println(participant.getName() + " : " + cardNumber.getName() + cardSuit.getCardSuitName());
        }
    }

    private void printFirstPlayersCards(final Participant participant) {
        System.out.print(participant.getName() + " : ");
        List<String> card = new ArrayList<>();
        for (int i = 0; i < FIRST_HIT_COUNT; i++) {
            ViewCardNumber cardNumber = ViewCardNumber.findCardNumber(participant.getCardNumber(i));
            ViewCardSuit cardSuit = ViewCardSuit.findCardSuit(participant.getCardSuit(i));
            card.add(cardNumber.getName() + cardSuit.getCardSuitName());
        }
        System.out.printf(String.join(", ", card));
        System.out.println();
    }
}
