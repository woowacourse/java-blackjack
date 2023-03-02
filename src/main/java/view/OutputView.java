package view;

import controller.BlackJackController;
import controller.BlackJackController.PlayerResult;
import domain.card.CardShape;
import domain.card.CardValue;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class OutputView {

    private static final Map<CardShape, String> SHAPE_MESSAGE_MAP = new EnumMap<>(CardShape.class);
    private static final Map<CardValue, String> VALUE_MESSAGE_MAP = new EnumMap<>(CardValue.class);
    private static final Map<PlayerResult, String> PLAYER_RESULT_MAP = new EnumMap<>(PlayerResult.class);

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
        PLAYER_RESULT_MAP.put(PlayerResult.WINNER, "승");
        PLAYER_RESULT_MAP.put(PlayerResult.LOSER, "패");
        PLAYER_RESULT_MAP.put(PlayerResult.DRAWER, "무");
    }

    public static void printAfterDeal(final List<? extends Player> participants) {
        System.out.println("딜러와 " + participants.stream().map(it -> it.name().value())
                                                .collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다");
    }

    public static void showPlayersState(final List<? extends Player> participants) {
        participants.forEach((participant) -> System.out.println(makeStateMessage(participant)));
    }

    public static void showPlayerState(final Player player) {
        System.out.println(makeStateMessage(player));
    }

    private static String makeStateMessage(final Player player) {
        return player.cardArea().cards().stream()
                     .map(card -> String.format("%s %s", VALUE_MESSAGE_MAP.get(card.cardValue()),
                                                SHAPE_MESSAGE_MAP.get(card.cardShape())))
                     .collect(Collectors.joining(", ", player.name().value() + "카드: ", ""));
    }

    public static void showParticipantsStateResult(final List<? extends Player> participants) {
        participants.forEach(OutputView::showPlayerStateResult);
    }

    public static void showPlayerStateResult(final Player player) {
        final String message = player.cardArea().cards().stream()
                                     .map(card -> String.format("%s %s", VALUE_MESSAGE_MAP.get(card.cardValue()),
                                                                SHAPE_MESSAGE_MAP.get(card.cardShape())))
                                     .collect(Collectors.joining(", ", player.name().value() + "카드: ",
                                                                 String.format(" - 결과: %d",
                                                                               player.cardArea().calculate())));
        System.out.println(message);
    }

    public static void dealerOneMoreCard() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showGameStatistic(BlackJackController.ResultDto resultDto) {
        showPlayerStateResult(resultDto.dealer());
        showParticipantsStateResult(resultDto.participants());
        final Map<Participant, PlayerResult> result = resultDto.participantsResult();

        final Map<PlayerResult, Long> winOrLose = result.keySet().stream()
                                                        .collect(Collectors.groupingBy(result::get, counting()));

        final String collect = winOrLose.keySet()
                                        .stream()
                                        .map(PlayerResult::reverse)
                                        .map(it -> winOrLose.get(it) + PLAYER_RESULT_MAP.get(it))
                                        .collect(Collectors.joining(" ", "딜러: ", ""));

        System.out.println(collect);

        resultDto.participants()
                 .stream()
                 .map(it -> it.name().value() + ": " + PLAYER_RESULT_MAP.get(result.get(it)))
                 .forEach(System.out::println);
    }

    public static void showDealerState(final Dealer dealer) {
        System.out.println(dealer.name().value() + ": " + VALUE_MESSAGE_MAP.get(
                dealer.cardArea().firstCard().cardValue()) + SHAPE_MESSAGE_MAP.get(
                dealer.cardArea().firstCard().cardShape()));
    }
}
