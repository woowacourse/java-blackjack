package blackjack.view;

import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Human;
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

    public static void printInitCards(final Players players, final Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printInitDealerHand(dealer);
        for (Player player : players.get()) {
            OutputView.printHumanHand(player);
        }
        System.out.println();
    }

    private static void printInitCardState(final Players players, final Dealer dealer) {
        List<String> playersNames = players.getNames();
        System.out.printf(INIT_CARD_MESSAGE,
                dealer.getName(),
                String.join(NAMES_DELIMITER, playersNames));
    }

    public static void printInitDealerHand(final Dealer dealer) {
        System.out.printf(CARD_STATE_MESSAGE, dealer.getName(), getCardsState(List.of(dealer.getInitCard())));
        System.out.println();
    }

    public static void printHumanHand(final Human human) {
        System.out.printf(CARD_STATE_MESSAGE, human.getName(), getCardsState(human.getRawCards()));
        System.out.println();
    }

    private static String getCardsState(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getDenomination().getInitial() + card.getSuit().getValue())
                .collect(Collectors.joining(CARDS_DELIMITER));
    }

    public static void printHandAndPoint(final Players players, final Dealer dealer) {
        System.out.println();
        OutputView.printHumanCardPointState(dealer);
        for (Player player : players.get()) {
            OutputView.printHumanCardPointState(player);
        }
    }

    public static void printHumanCardPointState(final Human human) {
        System.out.printf(CARD_STATE_MESSAGE + HUMAN_POINT_STATE + System.lineSeparator(),
                human.getName(), getCardsState(human.getRawCards()), human.getPoint().hashCode());
    }

    public static void printDealerHit() {
        System.out.println(DEALER_CARD_ADDED_MESSAGE);
    }

    public static void printResult(final Dealer dealer, final Integer money) {
        System.out.println(RESULT_FRONT_MESSAGE);
        printHumanResult(dealer, money);
    }

    public static void printHumanResult(final Human human, final int money) {
        System.out.printf(PLAYER_RESULT_MESSAGE, human.getName(), money);
    }
}
