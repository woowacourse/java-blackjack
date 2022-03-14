package view;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.MatchResult;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final long ZERO_FOR_DEFAULT = 0L;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다." + NEW_LINE;
    private static final String INFO_FOR_DEALER_ADD_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String INFO_PLAYER_CARD_AND_SCORE = "%s카드: %s - 결과: %d" + NEW_LINE;
    private static final String INFO_CARD_STATUS_AFTER_INITIAL_SPREAD = "%s: %s" + NEW_LINE;
    private static final String COLON_FOR_JOINING_NAME_AND_CARD = ": ";
    private static final String RESULT_TITLE = "## 최종 승패";
    private static final String DEALER_RESULT_PRINT_TEMPLATE = "딜러: %d승 %d무 %d패" + NEW_LINE;
    private static final String STRING_FOR_WIN = "승";
    private static final String STRING_FOR_LOSE = "패";
    private static final String STRING_FOR_DRAW = "무";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String GAMBLER_NAME_DELIMITER = ", ";
    private static final Map<Denomination, String> denominationName = new EnumMap<>(Denomination.class);
    private static final Map<Suit, String> suitName = new EnumMap<>(Suit.class);

    static {
        denominationName.put(Denomination.KING, "K");
        denominationName.put(Denomination.QUEEN, "Q");
        denominationName.put(Denomination.JACK, "J");
        denominationName.put(Denomination.ACE, "A");
        denominationName.put(Denomination.TWO, "2");
        denominationName.put(Denomination.THREE, "3");
        denominationName.put(Denomination.FOUR, "4");
        denominationName.put(Denomination.FIVE, "5");
        denominationName.put(Denomination.SIX, "6");
        denominationName.put(Denomination.SEVEN, "7");
        denominationName.put(Denomination.EIGHT, "8");
        denominationName.put(Denomination.NINE, "9");
        denominationName.put(Denomination.TEN, "10");

        suitName.put(Suit.CLUBS, "클로버");
        suitName.put(Suit.DIAMONDS, "다이아몬드");
        suitName.put(Suit.HEARTS, "하트");
        suitName.put(Suit.SPADES, "스페이드");
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
        return denominationName.get(card.getDenomination()) + suitName.get(card.getSuit());
    }

    public static void printLineSeparator() {
        System.out.println();
    }

    public static void printCards(CardsDto playerDto) {
        System.out.println(
                playerDto.getName() + COLON_FOR_JOINING_NAME_AND_CARD + getJoinedCardNames(
                        playerDto.getCards()));
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
        printDealerResult(blackJackResult);
        blackJackResult.getGamblerResult()
                .forEach(OutputView::printSingleGamblerResult);
    }

    private static void printDealerResult(BlackJackResult blackJackResult) {
        Map<MatchResult, Long> dealerResult = blackJackResult.getDealerResult();
        System.out.printf(
                DEALER_RESULT_PRINT_TEMPLATE,
                dealerResult.getOrDefault(WIN, ZERO_FOR_DEFAULT),
                dealerResult.getOrDefault(DRAW, ZERO_FOR_DEFAULT),
                dealerResult.getOrDefault(LOSE, ZERO_FOR_DEFAULT)
        );
    }

    public static void printSingleGamblerResult(String name, MatchResult matchResult) {
        System.out.println(name + COLON_FOR_JOINING_NAME_AND_CARD + getStringByMatchResult(matchResult));
    }

    private static String getStringByMatchResult(MatchResult matchResult) {
        if (matchResult == WIN) {
            return STRING_FOR_WIN;
        }

        if (matchResult == LOSE) {
            return STRING_FOR_LOSE;
        }

        return STRING_FOR_DRAW;
    }
}
