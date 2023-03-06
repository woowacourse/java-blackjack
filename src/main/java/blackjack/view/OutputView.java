package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.CardNumber;
import blackjack.domain.CardSuit;
import blackjack.domain.WinningResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for (int index = 0, end = participant.getReceivedCards().size(); index < end; index++) {
            CardNumber cardNumber = CardNumber.getCardNumber(participant.getCardNumber(index));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber.getName() + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.println();
    }

    public void printTotalCardsAndScore(final Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getReceivedCards().size(); index < end; index++) {
            CardNumber cardNumber = CardNumber.getCardNumber(participant.getCardNumber(index));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index));
            cards.add(cardNumber.getName() + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.print("- 결과 : " + participant.calculateCardNumber());
        System.out.println();
    }

    public void hitDealerCount(Dealer dealer) {
        System.out.println(HIT_DEALER_MESSAGE.repeat(dealer.getReceivedCards().size() - FIRST_CARD_COUNT));
    }

    public void printDealerWinORLose(final List<WinningResult> dealerResult) {
        System.out.print("딜러: ");
        System.out.print(WinningResult.WIN.getWinCount(dealerResult) + WinningResult.WIN.getName());
        System.out.print(WinningResult.PUSH.getPushCount(dealerResult) + WinningResult.PUSH.getName());
        System.out.println(WinningResult.LOSE.getLoseCount(dealerResult) + WinningResult.LOSE.getName());
    }

    public void printPlayerWinORLose(final Map<Player, WinningResult> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.println(player.getName() + " : " + playerResult.get(player).getName());
        }
    }

    private void printFirstDealerCards(final Dealer dealer) {
        CardNumber cardNumber = CardNumber.getCardNumber(dealer.getCardNumber(FIRST_CARD));
        String cardSuit = ViewCardSuit.getCardSuit(dealer.getCardSuit(FIRST_CARD));
        System.out.println(dealer.getName() + " : " + cardNumber.getName() + cardSuit);
    }

    private void printFirstPlayersCards(final Participant participant) {
        System.out.print(participant.getName() + " : ");
        List<String> card = new ArrayList<>();
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            CardNumber cardNumber = CardNumber.getCardNumber(participant.getCardNumber(i));
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(i));
            card.add(cardNumber.getName() + cardSuit);
        }
        System.out.printf(String.join(", ",card));
        System.out.println();
    }
}
