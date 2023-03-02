package view;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;
import domain.game.GameStatistic;
import domain.game.PlayerResult;
import domain.player.Dealer;
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
    private static final Map<PlayerResult, String> PLAYER_RESULT_MESSAGE_MAP = new EnumMap<>(PlayerResult.class);

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
        PLAYER_RESULT_MESSAGE_MAP.put(PlayerResult.WINNER, "승");
        PLAYER_RESULT_MESSAGE_MAP.put(PlayerResult.LOSER, "패");
        PLAYER_RESULT_MESSAGE_MAP.put(PlayerResult.DRAWER, "무");
    }

    public static void printAfterFirstDeal(final Dealer dealer, final List<Participant> participants) {
        System.out.printf("\n%s와 %s 에게 2장을 나누었습니다.\n",
                dealer.name().value(),
                participants.stream()
                        .map(it -> it.name().value())
                        .collect(Collectors.joining(DELIMITER))
        );
        showDealerCardAreaState(dealer);
        showPlayersCardAreaState(participants);
    }

    private static void showDealerCardAreaState(final Dealer dealer) {
        System.out.println(dealer.name().value() + ": " + makeCardMessage(dealer.firstCard()));
    }

    private static void showPlayersCardAreaState(final List<Participant> participants) {
        participants.stream()
                .map(OutputView::makeCardAreaStateMessage)
                .forEach(System.out::println);
    }

    public static void showPlayerCardAreaState(final Player player) {
        System.out.println(makeCardAreaStateMessage(player));
    }

    private static String makeCardAreaStateMessage(final Player player) {
        return player.cardArea().cards().stream()
                .map(OutputView::makeCardMessage)
                .collect(Collectors.joining(DELIMITER, player.name().value() + " 카드: ", EMPTY));
    }

    private static String makeCardMessage(final Card card) {
        return String.format("%s %s", VALUE_MESSAGE_MAP.get(card.cardValue()), SHAPE_MESSAGE_MAP.get(card.cardShape()));
    }

    public static void printDealerOneMoreCard() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showGameStatistic(final GameStatistic statistic) {
        showFinalCards(statistic);
        showFinalWinLose(statistic);
    }

    private static void showFinalCards(final GameStatistic statistic) {
        showPlayerCardAreaResultState(statistic.dealer());
        showParticipantsResultState(new ArrayList<>(statistic.resultPerParticipant().keySet()));
    }

    private static void showParticipantsResultState(final List<? extends Player> participants) {
        System.out.println();
        participants.forEach(OutputView::showPlayerCardAreaResultState);
    }

    private static void showPlayerCardAreaResultState(final Player player) {
        final String message = player.cardArea().cards().stream()
                .map(OutputView::makeCardMessage)
                .collect(Collectors.joining(DELIMITER, player.name().value() + "카드: ", String.format(" - 결과: %d", player.cardArea().calculate())));
        System.out.println(message);
    }

    private static void showFinalWinLose(final GameStatistic statistic) {
        System.out.println("\n## 최종 승패");
        showFinalDealerWinLose(statistic);
        showFinalParticipantsWinLose(statistic);
    }

    private static void showFinalDealerWinLose(final GameStatistic gameStatistic) {
        final Map<Participant, PlayerResult> resultPerParticipant = gameStatistic.resultPerParticipant();
        final Map<PlayerResult, Long> participantWinLoseCount = resultPerParticipant.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), counting()));
        final String dealerStatisticMessage = stringBuilder.append("딜러:")
                .append(dealerResultCount(participantWinLoseCount, PlayerResult.LOSER))
                .append(dealerResultCount(participantWinLoseCount, PlayerResult.DRAWER))
                .append(dealerResultCount(participantWinLoseCount, PlayerResult.WINNER))
                .toString();
        System.out.println(dealerStatisticMessage);
    }

    private static String dealerResultCount(final Map<PlayerResult, Long> participantWinLoseCount, final PlayerResult playerResult) {
        final Long count = participantWinLoseCount.getOrDefault(playerResult.reverse(), 0L);
        if (count == 0L) {
            return EMPTY;
        }
        return String.format(" %d%s", count, PLAYER_RESULT_MESSAGE_MAP.get(playerResult.reverse()));
    }

    private static void showFinalParticipantsWinLose(final GameStatistic statistic) {
        final Map<Participant, PlayerResult> resultPerParticipant = statistic.resultPerParticipant();
        final List<Participant> participants = statistic.participants();
        participants.stream()
                .map(it -> it.name().value() + ": " + PLAYER_RESULT_MESSAGE_MAP.get(resultPerParticipant.get(it)))
                .forEach(System.out::println);
    }
}
