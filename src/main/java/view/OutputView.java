package view;

import static model.participator.Dealer.DEALER_NAME;

import dto.AllCardsAndSumDto;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalProfitDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String CONNECTION_SURVEY = "와 ";
    private static final String SUFFIX_INIT_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String NAME_VALUE_DELIMITER = ": ";
    private static final String DELIMITER = ", ";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PROFIT_RESULT_MESSAGE = "## 최종 수익";
    private static final String CARD_MESSAGE = " 카드: ";
    private static final String CARD_SUM_MESSAGE = " - 결과: ";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String WON = "원";

    public static void printInit(AllParticipatorsDto allParticipatorsDto) {
        List<ParticipatorDto> playersDto = allParticipatorsDto.getPlayersDto();
        ParticipatorDto dealerDto = allParticipatorsDto.getDealerDto();
        System.out.println(
                dealerDto.getName() + CONNECTION_SURVEY + convertPlayerInLine(getNames(playersDto))
                        + SUFFIX_INIT_MESSAGE);
        printParticipatorNameAndCard(dealerDto);
        for (ParticipatorDto participatorDto : playersDto) {
            printParticipatorNameAndCard(participatorDto);
        }
    }

    private static List<String> getNames(List<ParticipatorDto> participators) {
        return participators.stream()
                .map(ParticipatorDto::getName)
                .collect(Collectors.toList());
    }

    private static void printParticipatorNameAndCard(ParticipatorDto participatorDto) {
        System.out.println(
                participatorDto.getName() + NAME_VALUE_DELIMITER + convertCardsInLine(participatorDto.getCards()));
    }

    private static String convertCardsInLine(List<String> cards) {
        return String.join(DELIMITER, cards);
    }

    private static String convertPlayerInLine(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name).append(DELIMITER);
        }
        sb.deleteCharAt(sb.lastIndexOf(DELIMITER));
        return sb.toString();
    }

    public static void printParticipatorHit(ParticipatorDto participatorDto) {
        printParticipatorNameAndCard(participatorDto);
    }

    public static void printHitDealer() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printMatchResult(TotalProfitDto totalProfitDto) {
        System.out.print(System.lineSeparator());
        System.out.println(PROFIT_RESULT_MESSAGE);

        System.out.println(DEALER_NAME + NAME_VALUE_DELIMITER + totalProfitDto.getDealerProfit() + WON);

        for (Entry<String, Long> entry : totalProfitDto.getPlayerProfits().entrySet()) {
            System.out.println(entry.getKey() + NAME_VALUE_DELIMITER + entry.getValue()+ WON);
        }
    }

    public static void printResult(AllCardsAndSumDto allCardsResultDto) {
        printResult(allCardsResultDto.getDealer(), allCardsResultDto.getDealerSum());
        printPlayersResult(allCardsResultDto.getPlayerResults());
    }

    private static void printPlayersResult(Map<ParticipatorDto, Integer> playerResults) {
        for (Entry<ParticipatorDto, Integer> entry : playerResults.entrySet()) {
            printResult(entry.getKey(), entry.getValue());
        }
    }

    private static void printResult(ParticipatorDto key, int value) {
        StringBuilder sb = new StringBuilder();
        sb.append(key.getName())
                .append(CARD_MESSAGE)
                .append(convertCardsInLine(key.getCards()))
                .append(CARD_SUM_MESSAGE)
                .append(value);
        System.out.println(sb);
    }

    public static void printException(String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
