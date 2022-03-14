package view;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.MatchResult;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import dto.ResultDto;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다." + NEW_LINE;
    private static final String INFO_FOR_DEALER_ADD_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String INFO_PLAYER_CARD_AND_SCORE = "%s카드: %s - 결과: %d" + NEW_LINE;
    private static final String INFO_CARD_STATUS_AFTER_INITIAL_SPREAD = "%s: %s" + NEW_LINE;
    private static final String COLON_FOR_JOINING_NAME_AND_CARD = ": ";
    private static final String RESULT_TITLE = "## 최종 승패";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String GAMBLER_NAME_DELIMITER = ", ";
    private static final Map<Denomination, String> DENOMINATION_NAME_MAPPER = new EnumMap<>(Denomination.class);
    private static final Map<Suit, String> SUIT_NAME_MAPPER = new EnumMap<>(Suit.class);
    private static final Map<MatchResult, String> MATCH_RESULT_MAPPER = new EnumMap<>(MatchResult.class);
    private static final String WIN_DRAW_LOSE_DELIMITER = " ";

    static {
        DENOMINATION_NAME_MAPPER.put(Denomination.KING, "K");
        DENOMINATION_NAME_MAPPER.put(Denomination.QUEEN, "Q");
        DENOMINATION_NAME_MAPPER.put(Denomination.JACK, "J");
        DENOMINATION_NAME_MAPPER.put(Denomination.ACE, "A");
        DENOMINATION_NAME_MAPPER.put(Denomination.TWO, "2");
        DENOMINATION_NAME_MAPPER.put(Denomination.THREE, "3");
        DENOMINATION_NAME_MAPPER.put(Denomination.FOUR, "4");
        DENOMINATION_NAME_MAPPER.put(Denomination.FIVE, "5");
        DENOMINATION_NAME_MAPPER.put(Denomination.SIX, "6");
        DENOMINATION_NAME_MAPPER.put(Denomination.SEVEN, "7");
        DENOMINATION_NAME_MAPPER.put(Denomination.EIGHT, "8");
        DENOMINATION_NAME_MAPPER.put(Denomination.NINE, "9");
        DENOMINATION_NAME_MAPPER.put(Denomination.TEN, "10");

        SUIT_NAME_MAPPER.put(Suit.CLUBS, "클로버");
        SUIT_NAME_MAPPER.put(Suit.DIAMONDS, "다이아몬드");
        SUIT_NAME_MAPPER.put(Suit.HEARTS, "하트");
        SUIT_NAME_MAPPER.put(Suit.SPADES, "스페이드");

        MATCH_RESULT_MAPPER.put(WIN, "승");
        MATCH_RESULT_MAPPER.put(DRAW, "무");
        MATCH_RESULT_MAPPER.put(LOSE, "패");
    }

    private OutputView() {
    }

    public static void printSpreadAnnouncement(String dealerName, List<String> gamblerNames) {
        System.out.println();
        System.out.printf(INFO_FOR_INITIAL_SPREAD, dealerName, String.join(GAMBLER_NAME_DELIMITER, gamblerNames));
    }

    public static void printInitialOpenCards(List<CardsDto> cardsDtos) {
        cardsDtos.forEach(
                cardsDto -> System.out.printf(
                        INFO_CARD_STATUS_AFTER_INITIAL_SPREAD,
                        cardsDto.getName(), getJoinedCardNames(cardsDto.getCards())
                ));
    }

    private static String getJoinedCardNames(List<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardName)
                .collect(joining(CARD_NAME_JOIN_CHARACTER));
    }

    private static String parseCardName(Card card) {
        return DENOMINATION_NAME_MAPPER.get(card.getDenomination()) + SUIT_NAME_MAPPER.get(card.getSuit());
    }

    public static void printLineSeparator() {
        System.out.println();
    }

    public static void printCards(CardsDto playerDto) {
        System.out.println(
                playerDto.getName() + COLON_FOR_JOINING_NAME_AND_CARD + getJoinedCardNames(playerDto.getCards()));
    }

    public static void printDealerAddCard(Dealer dealer) {
        System.out.printf(INFO_FOR_DEALER_ADD_CARD, dealer.getName());
    }

    public static void printCardAndScoreDtos(List<CardsAndScoreDto> cardsAndScoreDtos) {
        System.out.println();

        for (CardsAndScoreDto cardsAndScoreDto : cardsAndScoreDtos) {
            System.out.printf(INFO_PLAYER_CARD_AND_SCORE,
                    cardsAndScoreDto.getName(),
                    getJoinedCardNames(cardsAndScoreDto.getCards()),
                    cardsAndScoreDto.getScore());
        }
    }

    public static void printResult(BlackJackResult blackJackResult) {
        System.out.println(System.lineSeparator() + RESULT_TITLE);

        blackJackResult.getBlackjackResult()
                .entrySet()
                .forEach(OutputView::printSingleGamblerResult);
    }

    public static void printSingleGamblerResult(Map.Entry<String, ResultDto> entry) {
        System.out.println(
                entry.getKey() + COLON_FOR_JOINING_NAME_AND_CARD + getResultMessageByResultDto(entry.getValue()));
    }

    private static String getResultMessageByResultDto(ResultDto resultDto) {
        if (resultDto.isGamblerResult()) {
            return MATCH_RESULT_MAPPER.get(resultDto.getGamblerResult());
        }

        return getDealerResult(getDealerResultByResultDto(resultDto));
    }

    private static Map<MatchResult, Long> getDealerResultByResultDto(ResultDto resultDto) {
        return resultDto.getMatchResults()
                .stream()
                .collect(groupingBy(identity(), () -> new EnumMap<>(MatchResult.class), counting()));
    }

    private static String getDealerResult(Map<MatchResult, Long> dealerResult) {
        return Arrays.stream(MatchResult.values())
                .filter(dealerResult::containsKey)
                .map(matchResult -> dealerResult.get(matchResult) + MATCH_RESULT_MAPPER.get(matchResult))
                .collect(joining(WIN_DRAW_LOSE_DELIMITER));
    }
}
