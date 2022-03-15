package blackjack.view;

import static blackjack.domain.Rule.DEALER_HIT_STANDARD_SCORE;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Money;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String CARD_DELIMITER = ", ";
    private static final String COLON_AND_BLANK = ": ";

    public static void printInitialCards(final Dealer dealer, final Participants participants) {
        printDealMessage(dealer, participants);
        printDealerCard(dealer);
        printParticipantsCards(participants);
        printNewLine();
    }

    private static void printDealMessage(final Dealer dealer, final Participants participants) {
        final List<String> names = new ArrayList<>();
        for (Participant participant : participants) {
            names.add(participant.getName());
        }
        System.out.println("\n" + dealer.getName() + "와 " + String.join(CARD_DELIMITER, names) + "에게 2장을 나누었습니다.");
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card firstCard = dealer.openFirstCard();
        System.out.println(
                dealer.getName() + COLON_AND_BLANK + firstCard.getDenominationName() + firstCard.getSuitName());
    }

    private static void printParticipantsCards(final Participants participants) {
        for (Participant participant : participants) {
            printPlayerCards(participant);
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

    public static void printTotalMoney(final Map<Participant, Money> moneys,
                                       final String dealerName, final Money dealerMoney) {
        System.out.printf("%n## 최종 수익%n");
        printPlayerMoney(dealerName, dealerMoney.getValue());
        for (Participant participant : moneys.keySet()) {
            printPlayerMoney(participant.getName(), moneys.get(participant).getValue());
        }
    }

    private static void printPlayerMoney(final String name, final long money) {
        System.out.printf("%s: %d%n", name, money);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
