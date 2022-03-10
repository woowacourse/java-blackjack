package view;

import dto.TotalResultDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;

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
        System.out.println("## 최종 승패");

        System.out.println(convertDealerMatchCountInLine(resultDto));
        for (Entry<String, String > entry : resultDto.getPlayersMatchResult().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String convertDealerMatchCountInLine(TotalResultDto resultDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러: ");
        if (resultDto.getPlayerLoseCount() != 0) {
            sb.append(resultDto.getPlayerLoseCount()).append("승 ");
        }
        if (resultDto.getPlayerWinCount() != 0) {
            sb.append(resultDto.getPlayerWinCount()).append("패 ");
        }
        if (resultDto.getPlayerDrawCount() != 0) {
            sb.append(resultDto.getPlayerDrawCount()).append("무");
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
