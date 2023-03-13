package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.math.BigDecimal;
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
        String dealerName = participants.get(DEALER_INDEX);
        System.out.println();
        String stringBuilder = dealerName + "와 " +
                String.join(", ", participants) +
                "에게 2장을 나누었습니다.";
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
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index).name());
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index).name());
            cards.add(cardNumber + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.println();
    }

    public void printTotalCardsAndScore(final Participant participant) {
        System.out.print(participant.getName() + "카드: ");
        List<String> cards = new ArrayList<>();
        for (int index = 0, end = participant.getCardsCount(); index < end; index++) {
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(index).name());
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(index).name());
            cards.add(cardNumber + cardSuit);
        }
        System.out.print(String.join(", ", cards));
        System.out.print("- 결과 : " + participant.calculateCardNumber() + NEW_LINE);
    }

    public void printHitDealerCount(final Dealer dealer) {
        System.out.println(HIT_DEALER_MESSAGE.repeat(dealer.getCardsCount() - FIRST_CARD_COUNT));
    }

    public void printFinalRevenueStatement() {
        System.out.println("## 최종 수익");
    }

    public void printDealerProceeds(final BigDecimal dealerProceeds) {
        System.out.println("딜러: " + dealerProceeds);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printPlayersProceeds(final Map<Player, BigDecimal> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.println(player.getName()+": "+playerResult.get(player));
        }

    }

    private void printFirstDealerCards(final Dealer dealer) {
        String cardNumber = ViewCardNumber.getCardNumber(dealer.getCardNumber(FIRST_CARD).name());
        String cardSuit = ViewCardSuit.getCardSuit(dealer.getCardSuit(FIRST_CARD).name());
        System.out.println(dealer.getName() + " : " + cardNumber + cardSuit);
    }

    private void printFirstPlayersCards(final Participant participant) {
        System.out.print(participant.getName() + " : ");
        List<String> card = new ArrayList<>();
        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            String cardNumber = ViewCardNumber.getCardNumber(participant.getCardNumber(i).name());
            String cardSuit = ViewCardSuit.getCardSuit(participant.getCardSuit(i).name());
            card.add(cardNumber + cardSuit);
        }
        System.out.printf(String.join(", ",card)+NEW_LINE);
    }
}
