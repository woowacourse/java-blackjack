package view;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.dealer.DealerResult;
import domain.player.participant.Participant;
import domain.player.participant.ParticipantResult;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final Map<CardShape, String> SHAPE_MESSAGE_MAP = new EnumMap<>(CardShape.class);
    private static final Map<CardValue, String> VALUE_MESSAGE_MAP = new EnumMap<>(CardValue.class);
    private static final Map<ParticipantResult, String> PARTICIPANT_RESULT_MAP = new EnumMap<>(ParticipantResult.class);
    private static final Map<DealerResult, String> DEALER_RESULT_MAP = new EnumMap<>(DealerResult.class);

    private static final String DELIM = ", ";
    private static final String CARD_INFORMATION_FORMAT = "카드: ";
    private static final String DEALER_INFORMATION_FORMAT = "딜러: ";
    private static final String EMPTY_DELIM = " ";
    private static final String COLON = ": ";

    static {
        makeShapeMessage();
        makeValueMessage();
        makeParticipantResultMessage();
        makeDealerResultMessage();
    }

    private OutputView() {
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

    private static void makeParticipantResultMessage() {
        PARTICIPANT_RESULT_MAP.put(ParticipantResult.WINNER, "승");
        PARTICIPANT_RESULT_MAP.put(ParticipantResult.LOSER, "패");
        PARTICIPANT_RESULT_MAP.put(ParticipantResult.DRAWER, "무");
    }

    private static void makeDealerResultMessage() {
        DEALER_RESULT_MAP.put(DealerResult.WINNER, "승");
        DEALER_RESULT_MAP.put(DealerResult.LOSER, "패");
        DEALER_RESULT_MAP.put(DealerResult.DRAWER, "무");
    }

    public static void showDealtCardTo(final List<? extends Player> participants) {
        System.out.println("딜러와 " + printDrawing(participants) + "에게 2장을 나누었습니다");
    }

    private static String printDrawing(final List<? extends Player> participants) {
        return participants.stream()
                           .map(OutputView::getPlayerName)
                           .collect(Collectors.joining(DELIM));
    }

    public static void showStateOf(final Dealer dealer) {
        final Card dealerFistCard = dealer.cardArea().firstCard();

        System.out.println(getPlayerName(dealer)
                                   + COLON
                                   + VALUE_MESSAGE_MAP.get(dealerFistCard.cardValue())
                                   + SHAPE_MESSAGE_MAP.get(dealerFistCard.cardShape())
        );
    }

    public static void showStateOf(final List<? extends Player> participants) {
        participants.forEach(participant -> System.out.println(makeStateMessage(participant)));
    }

    public static void showStateOf(final Player player) {
        System.out.println(makeStateMessage(player));
    }

    private static String makeStateMessage(final Player player) {
        return player.cardArea()
                     .cards()
                     .stream()
                     .map(card -> String.format("%s %s",
                                                VALUE_MESSAGE_MAP.get(card.cardValue()),
                                                SHAPE_MESSAGE_MAP.get(card.cardShape())))
                     .collect(Collectors.joining(DELIM, getPlayerName(player)
                             + CARD_INFORMATION_FORMAT, ""));
    }

    public static void showParticipantsStateResult(final List<? extends Player> participants) {
        participants.forEach(OutputView::showPlayerStateResult);
    }

    public static void showPlayerStateResult(final Player player) {
        final String message = player.cardArea().cards().stream()
                                     .map(card -> String.format("%s %s",
                                                                VALUE_MESSAGE_MAP.get(card.cardValue()),
                                                                SHAPE_MESSAGE_MAP.get(card.cardShape())))
                                     .collect(Collectors.joining(DELIM, getPlayerName(player)
                                                                         + CARD_INFORMATION_FORMAT,
                                                                 String.format(" - 결과: %d",
                                                                               player.cardArea().calculate())));

        System.out.println(message);
    }

    private static String getPlayerName(final Player player) {
        return player.name().value();
    }

    public static void dealerOneMoreCard() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showDealerScoreBoard(final Map<DealerResult, Long> scoreBoard) {

        System.out.println(scoreBoard.keySet()
                                     .stream()
                                     .map(dealerResult -> scoreBoard.get(dealerResult)
                                             + DEALER_RESULT_MAP.get(dealerResult))
                                     .collect(Collectors.joining(EMPTY_DELIM, DEALER_INFORMATION_FORMAT, "")));
    }

    public static void showParticipantsScoreBoard(final Map<Participant, ParticipantResult> scoreBoard,
                                                  final List<Participant> participants) {

        participants.stream()
                    .map(participant -> getPlayerName(participant)
                            + COLON
                            + PARTICIPANT_RESULT_MAP.get(scoreBoard.get(participant))
                    )
                    .forEach(System.out::println);
    }
}
