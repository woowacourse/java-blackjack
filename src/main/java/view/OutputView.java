package view;

import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.card.PlayingCard;
import domain.player.Dealer;
import dto.CardsAndScoreDto;
import dto.CardsDto;
import dto.NameDto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import view.util.NameMapper;
import vo.Name;
import vo.Revenue;

public class OutputView {
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String INFO_FOR_DEALER_ADD_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다.%n";
    private static final String INFO_PLAYER_CARD_AND_SCORE = "%s카드: %s - 결과: %d%n";
    private static final String INFO_CARD_STATUS_AFTER_INITIAL_SPREAD = "%s: %s%n";
    private static final String COLON_FOR_NAME_AND_REVENUE = ": ";
    private static final String RESULT_TITLE = "## 최종 수익";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String GAMBLER_NAME_DELIMITER = ", ";
    private static final String ERROR_FOR_NO_DEALER_FOUND = "[ERROR] 딜러를 확인하지 못했습니다";
    private static final DecimalFormat REVENUE_FORMAT = new DecimalFormat("#");

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

    private static String getJoinedCardNames(List<PlayingCard> cards) {
        return cards.stream()
                .map(NameMapper::getCardName)
                .collect(joining(CARD_NAME_JOIN_CHARACTER));
    }

    public static void printLineSeparator() {
        System.out.println();
    }

    public static void printCards(CardsDto playerDto) {
        System.out.println(
                playerDto.getName() + COLON_FOR_NAME_AND_REVENUE + getJoinedCardNames(playerDto.getCards()));
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

    public static void printSingleGamblerResult(Map.Entry<Name, Revenue> entry) {
        Name name = entry.getKey();
        Revenue revenue = entry.getValue();
        System.out.println(name.getName() + COLON_FOR_NAME_AND_REVENUE + REVENUE_FORMAT.format(revenue.getRevenue()));
    }
}
