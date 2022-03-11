package view;

import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.Arrays;
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
        List<String> playerNames = getNames(playersDto);
        System.out.println(
                dealerDto.getName() + CONNECTION_SURVEY + convertPlayerInLine(playerNames) + SUFFIX_INIT_MESSAGE);
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

    public static void printParticipatorHit(boolean flag, ParticipatorDto participatorDto) {
        if (!flag && participatorDto.getCards().size() > 2) {
            return;
        }
        printParticipatorNameAndCard(participatorDto);
    }

    public static void printHitDealer(ParticipatorDto dealerDto) {
        if (isReceived(dealerDto)) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    private static boolean isReceived(ParticipatorDto dealerDto) {
        return dealerDto.getCards().size() != 2;
    }

    public static void printMatchResult(TotalResultDto resultDto) {
        Map<String, List<String>> dealerResult = resultDto.getDealerMatchResult();
        Map<String, String> playerResults = resultDto.getPlayersMatchResult();

        System.out.println("## 최종 승패");
        printDealerMatchResult(dealerResult);
        printPlayersMatchResult(playerResults);
    }

    private static void printPlayersMatchResult(Map<String, String> playerResults) {
        for (Entry<String, String> stringStringEntry : playerResults.entrySet()) {
            printResult(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
    }

    private static void printResult(String key, String value) {
        System.out.println(key + ": " + value);
    }

    private static void printDealerMatchResult(Map<String, List<String>> dealerResult) {
        for (Entry<String, List<String>> entry : dealerResult.entrySet()) {
            printResult(entry.getKey(), entry.getValue());
        }
    }

    private static void printResult(String key, List<String> value) {
        StringBuilder sb = new StringBuilder();
        sb.append(key)
                .append(": ")
                .append(convertDealerResult(value));
        System.out.println(sb);
    }

    private static String convertDealerResult(List<String> value) {
        StringBuilder sb = new StringBuilder();
        if (value.contains("승")) {
            final int count = (int) value.stream().filter(result -> result.equals("승")).count();
            sb.append(count + "승");
        }
        if (value.contains("패")) {
            final int count = (int) value.stream().filter(result -> result.equals("패")).count();
            sb.append(count + "패");
        }
        if (value.contains("무")) {
            final int count = (int) value.stream().filter(result -> result.equals("무")).count();
            sb.append(count + "무");
        }
        return sb.toString();
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
