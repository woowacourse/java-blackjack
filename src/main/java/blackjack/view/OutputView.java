package blackjack.view;

import blackjack.domain.*;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final int FIRST_CARD = 0;
    private static final int DEALER_INDEX = 0;
    public static final String HIT_DEALER_MESSAGE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    public static final int FIRST_CARD_COUNT = 2;

    public void printParticipants(final List<String> participants) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder(participants.remove(DEALER_INDEX));
        stringBuilder.append("와 ");
        stringBuilder.append(String.join(", ", participants));
        stringBuilder.append("에게 2장을 나누었습니다.");
        System.out.println(stringBuilder);
    }

    public void printParticipantsCard(final Dealer dealer, final List<Player> players) {
        printFirstDealerCards(dealer);
        for (Player player : players) {
            printFirstPlayersCards(player);
        }
    }

    public void printCurrentCards(final Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getCardsCount(); index < end; index++) {
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.println();
    }

    public void printTotalCardsAndScore(final Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getCardsCount(); index < end; index++) {
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.print("- 결과 : " + participant.calculateCardNumber() + NEW_LINE);
    }

    public void printHitDealerCount(Dealer dealer) {
        System.out.println(HIT_DEALER_MESSAGE.repeat(dealer.getCardsCount() - FIRST_CARD_COUNT));
    }

    public void printAllWinORLose(final BlackjackGameResult blackjackGameResult) {
        printDealerWinORLose(blackjackGameResult);
        for (Player player : blackjackGameResult.getGameResult().keySet()) {
            System.out.println(player.getName() + " : " +
                    ViewWinningResult.getWinningResultName(blackjackGameResult.NameByPlayer(player)));
        }
    }

    public void printDealerWinORLose(final BlackjackGameResult blackjackGameResult) {
        System.out.print("딜러: ");
        System.out.print(blackjackGameResult.getDealerWinCount() +
                ViewWinningResult.getWinningResultName(WinningResult.WIN));
        System.out.print(blackjackGameResult.getDealerPushCount() +
                ViewWinningResult.getWinningResultName(WinningResult.PUSH));
        System.out.println(blackjackGameResult.getDealerLoseCount() +
                ViewWinningResult.getWinningResultName(WinningResult.LOSE));
    }

    private void printFirstDealerCards(final Dealer dealer) {
        String cardNumber = ViewCardNumber.getCardNumber(dealer.getCardNumber(FIRST_CARD));
        String cardSuit = ViewCardSuit.getCardSuit(dealer.getCardSuit(FIRST_CARD));
        System.out.println(dealer.getName() + " : " + cardNumber + cardSuit);
    }

    private void printFirstPlayersCards(final Participant participant) {
        System.out.print(participant.getName() + " : ");
        List<String> card = new ArrayList<>();
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(i));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(i));
            card.add(cardNumber + cardSuit);
        }
        System.out.printf(String.join(", ",card)+NEW_LINE);
    }
}
