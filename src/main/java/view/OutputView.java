package view;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.MatchResult;
import domain.card.PlayingCard;
import domain.player.Dealer;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import dto.NameDto;
import dto.ResultDto;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import view.util.NameMapper;

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
    private static final String WIN_DRAW_LOSE_DELIMITER = " ";
    private static final String ERROR_FOR_NO_DEALER_FOUND = "[ERROR] 딜러를 확인하지 못했습니다";

    private OutputView() {
    }

    public static void printSpreadAnnouncement(List<NameDto> names) {
        System.out.println();
        NameDto dealerNameDto = getDealerNameByNameDtos(names);
        String gamblerNames = getGamblerNamesByNameDtos(names);

        System.out.printf(INFO_FOR_INITIAL_SPREAD, dealerNameDto.getName(),
                String.join(GAMBLER_NAME_DELIMITER, gamblerNames));
    }

    private static NameDto getDealerNameByNameDtos(List<NameDto> names) {
        return names.stream()
                .filter(NameDto::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(ERROR_FOR_NO_DEALER_FOUND));
    }

    private static String getGamblerNamesByNameDtos(List<NameDto> names) {
        return names.stream()
                .filter(NameDto::isGambler)
                .map(NameDto::getName)
                .collect(joining(GAMBLER_NAME_DELIMITER));
    }

    public static void printInitialOpenCards(List<CardsDto> cardsDtos) {
        cardsDtos.forEach(
                cardsDto -> System.out.printf(
                        INFO_CARD_STATUS_AFTER_INITIAL_SPREAD,
                        cardsDto.getName(), getJoinedCardNames(cardsDto.getCards())
                ));
    }

    private static String getJoinedCardNames(List<PlayingCard> playingPlayingCards) {
        return playingPlayingCards.stream()
                .map(NameMapper::getCardName)
                .collect(joining(CARD_NAME_JOIN_CHARACTER));
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
            return NameMapper.getResultName(resultDto.getGamblerResult());
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
                .map(matchResult -> dealerResult.get(matchResult) + NameMapper.getResultName(matchResult))
                .collect(joining(WIN_DRAW_LOSE_DELIMITER));
    }
}
