package blackjack.view;

import static blackjack.domain.Rule.DEALER_HIT_STANDARD_SCORE;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String CARD_DELIMITER = ", ";
    private static final String COLON_AND_BLANK = ": ";

    public static void printInitialCards(final Dealer dealer, final Guests guests) {
        printDealMessage(dealer, guests);
        printDealerCard(dealer);
        printGuestsCards(guests);
        printNewLine();
    }

    private static void printDealMessage(final Dealer dealer, final Guests guests) {
        final List<String> names = new ArrayList<>();
        for (Guest guest : guests) {
            names.add(guest.getName());
        }
        System.out.println("\n" + dealer.getName() + "와 " + String.join(CARD_DELIMITER, names) + "에게 2장을 나누었습니다.");
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card firstCard = dealer.openFirstCard();
        System.out.println(
                dealer.getName() + COLON_AND_BLANK + firstCard.getDenominationName() + firstCard.getSuitName());
    }

    private static void printGuestsCards(final Guests guests) {
        for (Guest guest : guests) {
            printPlayerCards(guest);
        }
    }

    public static void printPlayerCards(final Player player) {
        final List<String> cards = new ArrayList<>();
        for (Card card : player.getCards()) {
            cards.add(card.getDenominationName() + card.getSuitName());
        }
        System.out.println(player.getName() + "카드: " + String.join(CARD_DELIMITER, cards));
    }

    public static void printDealerGetCardMessage(final Dealer dealer) {
        System.out.println(dealer.getName() + "는 " + DEALER_HIT_STANDARD_SCORE.getValue() + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(final Player player, final int totalScore) {
        final List<String> cards = new ArrayList<>();
        for (Card card : player.getCards()) {
            cards.add(card.getDenominationName() + card.getSuitName());
        }
        System.out.println(player.getName() + "카드: " + String.join(CARD_DELIMITER, cards) + " - 결과: " + totalScore);
    }

    public static void printTotalProfit(final Map<Guest, Money> guestProfit,
                                        final String dealerName, final Money dealerProfit) {
        System.out.printf("%n## 최종 수익%n");
        printPlayerProfit(dealerName, dealerProfit.getValue());
        for (Guest guest : guestProfit.keySet()) {
            printPlayerProfit(guest.getName(), guestProfit.get(guest).getValue());
        }
    }

    private static void printPlayerProfit(final String name, final long money) {
        System.out.printf("%s: %d%n", name, money);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
