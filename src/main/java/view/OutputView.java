package view;

import controller.GameStatisticResponse;
import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;
import domain.player.Dealer;
import domain.player.DealerCompeteResult;
import domain.player.Participant;
import domain.player.Player;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class OutputView {

    private static final StringBuilder stringBuilder = new StringBuilder();

    private static final Map<CardShape, String> SHAPE_MESSAGE_MAP = new EnumMap<>(CardShape.class);
    private static final Map<CardValue, String> VALUE_MESSAGE_MAP = new EnumMap<>(CardValue.class);
    private static final Map<DealerCompeteResult, String> DEALER_COMPETE_MESSAGE_MAP = new EnumMap<>(DealerCompeteResult.class);

    private static final String EMPTY = "";
    private static final String DELIMITER = ", ";

    static {
        makeShapeMessage();
        makeValueMessage();
        makePlayerResultMessage();
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

    private static void makePlayerResultMessage() {
        DEALER_COMPETE_MESSAGE_MAP.put(DealerCompeteResult.WIN, "승");
        DEALER_COMPETE_MESSAGE_MAP.put(DealerCompeteResult.LOSE, "패");
        DEALER_COMPETE_MESSAGE_MAP.put(DealerCompeteResult.DRAW, "무");
    }

    public static void printAfterFirstDeal(final Dealer dealer, final List<Player> players) {
        final String message = String.format("\n%s와 %s 에게 2장을 나누었습니다.", dealer.nameValue(), playerNames(players));
        System.out.println(message);
        showDealerCardAreaState(dealer);
        players.forEach(OutputView::showPlayerCardAreaState);
    }

    private static String playerNames(final List<Player> players) {
        return players.stream()
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
    public static void showPlayerCardAreaState(final Player player) {
        final String message = makeCardAreaStateMessage(player);
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
        System.out.println("\n딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    /**
     * 딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
     * pobi 카드: 2하트, 8스페이드, A 클로버 - 결과: 21
     * jason 카드: 7클로버, K 스페이드 - 결과: 17
     * <br>
     * ## 최종 승패
     * 딜러: 1승 1패
     * pobi: 승
     * jason: 패
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
        participant.addAll(statistic.players());
        showParticipantsResultState(participant);
    }

    private static void showParticipantsResultState(final List<? extends Participant> participants) {
        participants.forEach(OutputView::showParticipantsCardAreaResultState);
    }

    /* jason 카드: 7클로버, K 스페이드 - 결과: 17 */
    private static void showParticipantsCardAreaResultState(final Participant participant) {
        final String message = participant.cardArea().cards().stream()
                .map(OutputView::makeCardMessage)
                .collect(Collectors.joining(DELIMITER, participant.nameValue() + "카드: ", String.format(" - 결과: %d", participant.cardArea().calculate())));
        System.out.println(message);
    }

    private static void showFinalWinLose(final GameStatisticResponse statistic) {
        System.out.println("\n## 최종 승패");
        showFinalDealerWinLose(statistic);
        showFinalParticipantsWinLose(statistic);
    }

    private static void showFinalDealerWinLose(final GameStatisticResponse gameStatisticResponse) {
        final Map<Player, DealerCompeteResult> resultPerParticipant = gameStatisticResponse.dealerResultPerPlayer();
        final Map<DealerCompeteResult, Long> dealerWinLoseCount = resultPerParticipant.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), counting()));
        final String dealerStatisticMessage = stringBuilder.append("딜러:")
                .append(dealerResultCount(dealerWinLoseCount, DealerCompeteResult.WIN))
                .append(dealerResultCount(dealerWinLoseCount, DealerCompeteResult.DRAW))
                .append(dealerResultCount(dealerWinLoseCount, DealerCompeteResult.LOSE))
                .toString();
        System.out.println(dealerStatisticMessage);
    }

    private static String dealerResultCount(final Map<DealerCompeteResult, Long> dealerWinLoseCount, final DealerCompeteResult dealerCompeteResult) {
        final Long count = dealerWinLoseCount.getOrDefault(dealerCompeteResult, 0L);
        if (count == 0L) {
            return EMPTY;
        }
        return String.format(" %d%s", count, DEALER_COMPETE_MESSAGE_MAP.get(dealerCompeteResult));
    }

    private static void showFinalParticipantsWinLose(final GameStatisticResponse statistic) {
        final Map<Player, DealerCompeteResult> resultPerParticipant = statistic.dealerResultPerPlayer();
        final List<Player> players = statistic.players();
        players.stream()
                .map(it -> it.nameValue() + ": " + DEALER_COMPETE_MESSAGE_MAP.get(resultPerParticipant.get(it).reverse()))
                .forEach(System.out::println);
    }
}
