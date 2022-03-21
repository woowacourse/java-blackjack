package blackjack.view;

import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Participant;
import blackjack.domain.participant.human.Player;
import java.util.List;
import java.util.stream.Collectors;

public final class OutputView {
    private static final String NAMES_DELIMITER = ",";
    private static final String CARDS_DELIMITER = ", ";
    private static final String INIT_CARD_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장의 카드를 나누었습니다."
            + System.lineSeparator();
    private static final String CARD_STATE_MESSAGE = "%s카드: %s";
    private static final String HUMAN_POINT_STATE = " - 결과 : %s";
    private static final String DEALER_CARD_ADDED_MESSAGE = System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PLAYER_RESULT_MESSAGE = "%s: %d" + System.lineSeparator();
    private static final String RESULT_FRONT_MESSAGE = System.lineSeparator() + "## 최종 수익";

    public static void printInitHands(final Players players, final Dealer dealer) {
        printInitCardState(players, dealer);
        printInitDealerHand(dealer);
        players.printHand(OutputView::printHumanHand);
        System.out.println();
    }

    private static void printInitCardState(final Players players, final Dealer dealer) {
        List<String> playersNames = players.getNames();
        System.out.printf(INIT_CARD_MESSAGE,
                dealer.getName(),
                String.join(NAMES_DELIMITER, playersNames));
    }

    public static void printInitDealerHand(final Dealer dealer) {
        System.out.printf(CARD_STATE_MESSAGE, dealer.getName(),
                getCardsState(List.of(dealer.getFirstCard())));
        System.out.println();
    }

    public static void printHand(final Player player) {
        if (player.isInitState() || !player.getState().isFinished()) {
            printHumanHand(player);
        }
    }

    private static void printHumanHand(final Participant participant) {
        System.out.printf(CARD_STATE_MESSAGE, participant.getName(), getCardsState(participant.getRawCards()));
        System.out.println();
    }

    private static String getCardsState(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(CARDS_DELIMITER));
    }

    public static void printHandAndPoint(final Players players, final Dealer dealer) {
        System.out.println();
        printHumanCardPointState(dealer);
        players.printHand(OutputView::printHumanCardPointState);
    }

    private static void printHumanCardPointState(final Participant participant) {
        System.out.printf(CARD_STATE_MESSAGE + HUMAN_POINT_STATE + System.lineSeparator(),
                participant.getName(), getCardsState(participant.getRawCards()), participant.getPoint());
    }

    public static void printDealerHit(boolean isPrint) {
        if (isPrint) {
            System.out.println(DEALER_CARD_ADDED_MESSAGE);
        }
    }

    public static void printDealerProfit(final Dealer dealer, final Integer money) {
        System.out.println(RESULT_FRONT_MESSAGE);
        printProfit(dealer, money);
    }

    public static void printProfit(final Participant participant, final int money) {
        System.out.printf(PLAYER_RESULT_MESSAGE, participant.getName(), money);
    }
}
