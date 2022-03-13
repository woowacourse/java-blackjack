package view;

import dto.AllCardsAndSumDto;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String CONNECTION_SURVEY = "와 ";
    private static final String SUFFIX_INIT_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String NAME_CARD_DELIMITER = ": ";
    private static final String DELIMITER = ", ";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String MATCH_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_MATCH_MESSAGE = "딜러: ";
    private static final String SPACE = " ";
    private static final String CARD_MESSAGE = " 카드: ";
    private static final String CARD_SUM_MESSAGE = " - 결과: ";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public static void printInit(AllParticipatorsDto allParticipatorsDto) {
        List<ParticipatorDto> playersDto = allParticipatorsDto.getPlayersDto();
        ParticipatorDto dealerDto = allParticipatorsDto.getDealerDto();
        System.out.println(
                dealerDto.getName() + CONNECTION_SURVEY + convertPlayerInLine(getNames(playersDto))
                        + SUFFIX_INIT_MESSAGE);
        printParticipatorNameAndCard(dealerDto);
        for (int i = 0; i < playersDto.size(); i++) {
            printParticipatorNameAndCard(playersDto.get(i));
        }
    }

    private static List<String> getNames(List<ParticipatorDto> participators) {
        return participators.stream()
                .map(ParticipatorDto::getName)
                .collect(Collectors.toList());
    }

    private static void printParticipatorNameAndCard(ParticipatorDto participatorDto) {
        System.out.println(
                participatorDto.getName() + NAME_CARD_DELIMITER + convertCardsInLine(participatorDto.getCards()));
    }

    private static String convertCardsInLine(List<String> cards) {
        return String.join(DELIMITER, cards);
    }

    private static String convertPlayerInLine(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i)).append(DELIMITER);
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

    public static void printMatchResult(TotalResultDto totalResultDto) {
        System.out.print(System.lineSeparator());
        System.out.println(MATCH_RESULT_MESSAGE);

        System.out.println(convertDealerMatchCountInLine(totalResultDto.getDealerMatchCount()));

        Map<String, String> playerResults = totalResultDto.getPlayersMatchResult();
        for (Entry<String, String> entry : playerResults.entrySet()) {
            System.out.println(entry.getKey() + NAME_CARD_DELIMITER + entry.getValue());
        }
    }

    private static String convertDealerMatchCountInLine(Map<String, Long> results) {
        StringBuilder sb = new StringBuilder();
        sb.append(DEALER_MATCH_MESSAGE);
        for (Entry<String, Long> entry : results.entrySet()) {
            printEachResult(sb, entry);
        }
        return sb.toString();
    }

    private static void printEachResult(StringBuilder builder, Entry<String, Long> matchEntry) {
        if (matchEntry.getValue() != 0) {
            builder.append(matchEntry.getValue()).append(matchEntry.getKey()).append(SPACE);
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
