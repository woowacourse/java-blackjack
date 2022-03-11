package view;

import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private static final String CONNECTION_SURVEY = "와 ";
    private static final String SUFFIX_INIT_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String NAME_CARD_DELIMITER = ": ";
    private static final String DELIMITER = ", ";

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
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");

    }

    public static void printMatchResult(Map<String, String> results) {
        System.out.println("## 최종 승패");

        System.out.println(convertDealerMatchCountInLine(results));
        for (Entry<String, String> entry : results.entrySet()) {
            System.out.println(entry.getKey() + NAME_CARD_DELIMITER + entry.getValue());
        }
    }

    private static String convertDealerMatchCountInLine(Map<String, String> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러: ");
        if (countOf(results, "LOSE") != 0) {
            sb.append(countOf(results, "LOSE")).append("승 ");
        }
        if (countOf(results, "WIN") != 0) {
            sb.append(countOf(results, "WIN")).append("패 ");
        }
        if (countOf(results, "DRAW") != 0) {
            sb.append(countOf(results, "DRAW")).append("무");
        }
        return sb.toString();
    }

    private static long countOf(Map<String, String> results, String target) {
        return new ArrayList<>(results.values()).stream()
                .filter(result -> result.equals(target))
                .count();
    }

    public static void printResult(Map<ParticipatorDto, Integer> result) {
        for (Entry<ParticipatorDto, Integer> entry : result.entrySet()) {
            printResult(entry.getKey(), entry.getValue());
        }
    }

    private static void printResult(ParticipatorDto key, int value) {
        StringBuilder sb = new StringBuilder();
        sb.append(key.getName())
                .append(" 카드: ")
                .append(convertCardsInLine(key.getCards()))
                .append(" - 결과: ")
                .append(value);
        System.out.println(sb);
    }
}
