package view;

import controller.GameStatisticResponse;
import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Participant;
import domain.player.Revenue;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final Map<CardShape, String> SHAPE_MESSAGE_MAP = new EnumMap<>(CardShape.class);
    private static final Map<CardValue, String> VALUE_MESSAGE_MAP = new EnumMap<>(CardValue.class);

    private static final String EMPTY = "";
    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    static {
        makeShapeMessage();
        makeValueMessage();
    }

    private static void makeShapeMessage() {
        SHAPE_MESSAGE_MAP.put(CardShape.DIAMOND, "다이아몬드");
        SHAPE_MESSAGE_MAP.put(CardShape.CLOVER, "클로버");
        SHAPE_MESSAGE_MAP.put(CardShape.HEART, "하트");
        SHAPE_MESSAGE_MAP.put(CardShape.SPADE, "스페이드");
    }

    private static void makeValueMessage() {
        VALUE_MESSAGE_MAP.put(CardValue.TWO, "2");
        VALUE_MESSAGE_MAP.put(CardValue.THREE, "3");
        VALUE_MESSAGE_MAP.put(CardValue.FOUR, "4");
        VALUE_MESSAGE_MAP.put(CardValue.FIVE, "5");
        VALUE_MESSAGE_MAP.put(CardValue.SIX, "6");
        VALUE_MESSAGE_MAP.put(CardValue.SEVEN, "7");
        VALUE_MESSAGE_MAP.put(CardValue.EIGHT, "8");
        VALUE_MESSAGE_MAP.put(CardValue.NINE, "9");
        VALUE_MESSAGE_MAP.put(CardValue.TEN, "10");
        VALUE_MESSAGE_MAP.put(CardValue.KING, "킹");
        VALUE_MESSAGE_MAP.put(CardValue.QUEEN, "퀸");
        VALUE_MESSAGE_MAP.put(CardValue.JACK, "잭");
        VALUE_MESSAGE_MAP.put(CardValue.ACE, "A");
    }

    public static void printAfterFirstDeal(final Dealer dealer, final List<Gambler> gamblers) {
        final String message = String.format("%s와 %s 에게 2장을 나누었습니다.", dealer.nameValue(), gamblerNames(gamblers));
        System.out.println(NEW_LINE + message);
        showDealerCardAreaState(dealer);
        gamblers.forEach(OutputView::showGamblerCardAreaState);
    }

    private static String gamblerNames(final List<Gambler> gamblers) {
        return gamblers.stream()
                .map(Participant::nameValue)
                .collect(Collectors.joining(DELIMITER));
    }

    // ex: 딜러카드: 2 다이아
    private static void showDealerCardAreaState(final Dealer dealer) {
        System.out.println(dealer.nameValue() + ": " + makeCardMessage(dealer.firstCard()));
    }

    /**
     * 출력 : 말랑카드: 2 다이아, 잭 클로버
     */
    public static void showGamblerCardAreaState(final Gambler gambler) {
        final String message = makeCardAreaStateMessage(gambler);
        System.out.println(message);
    }

    /* 말랑카드: 2 다이아, 잭 클로버 */
    private static String makeCardAreaStateMessage(final Participant participant) {
        return participant.cardArea().cards().stream()
                .map(OutputView::makeCardMessage)
                .collect(Collectors.joining(DELIMITER, participant.nameValue() + " 카드: ", EMPTY));
    }

    /* ex: 2 다이아*/
    private static String makeCardMessage(final Card card) {
        return String.format("%s %s", VALUE_MESSAGE_MAP.get(card.cardValue()), SHAPE_MESSAGE_MAP.get(card.cardShape()));
    }

    public static void printDealerOneMoreCard() {
        System.out.println(NEW_LINE + "딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    /**
     * 딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
     * pobi 카드: 2하트, 8스페이드, A 클로버 - 결과: 21
     * jason 카드: 7클로버, K 스페이드 - 결과: 17
     * <br>
     * ## 최종 수익
     * 딜러: 10000
     * pobi: 10000
     * jason: -20000
     */
    public static void showGameStatistic(final GameStatisticResponse statistic) {
        showFinalCards(statistic);
        showFinalWinLose(statistic);
    }

    /**
     * 딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
     * pobi 카드: 2하트, 8스페이드, A 클로버 - 결과: 21
     * jason 카드: 7클로버, K 스페이드 - 결과: 17
     */
    private static void showFinalCards(final GameStatisticResponse statistic) {
        System.out.println();
        final List<Participant> participant = new ArrayList<>();
        participant.add(statistic.dealer());
        participant.addAll(statistic.gamblers());
        showParticipantsResultState(participant);
    }

    private static void showParticipantsResultState(final List<? extends Participant> participants) {
        participants.forEach(OutputView::showParticipantsCardAreaResultState);
    }

    /* jason 카드: 7클로버, K 스페이드 - 결과: 17 */
    private static void showParticipantsCardAreaResultState(final Participant participant) {
        final String message = participant.cardArea().cards().stream()
                .map(OutputView::makeCardMessage)
                .collect(Collectors.joining(DELIMITER, participant.nameValue() + "카드: ", String.format(" - 결과: %d", participant.score().value())));
        System.out.println(message);
    }

    private static void showFinalWinLose(final GameStatisticResponse statistic) {
        System.out.println(NEW_LINE + "## 최종 수익");
        showFinalDealerRevenue(statistic);
        showFinalGamblersRevenue(statistic);
    }

    private static void showFinalDealerRevenue(final GameStatisticResponse gameStatisticResponse) {
        final Revenue dealerRevenue = gameStatisticResponse.participantRevenueMap().remove(gameStatisticResponse.dealer());
        System.out.println("딜러: " + dealerRevenue.amount());
    }

    private static void showFinalGamblersRevenue(final GameStatisticResponse statistic) {
        final Map<Participant, Revenue> gamblerRevenueMap = statistic.participantRevenueMap();
        final List<Gambler> gamblers = statistic.gamblers();
        gamblers.stream()
                .map(it -> it.nameValue() + ": " + gamblerRevenueMap.get(it).amount())
                .forEach(System.out::println);
    }
}
