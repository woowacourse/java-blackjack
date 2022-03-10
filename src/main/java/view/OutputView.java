package view;

import java.util.List;
import java.util.stream.Collectors;
import dto.InitGameDto;
import dto.ParticipatorDto;

public class OutputView {

    private static final String CONNECTION_SURVEY = "와 ";
    private static final String SUFFIX_INIT_MESSAGE = "에게 2장을 나누었습니다.";
    private static final String NAME_CARD_DELIMITER = ": ";
    private static final String DELIMITER = ", ";

    public static void printInit(InitGameDto initGameDto) {
        List<ParticipatorDto> playersDto = initGameDto.getPlayersDto();
        ParticipatorDto dealerDto = initGameDto.getDealerDto();
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
//        if (!flag && participatorDto.getCards().size() > 2) {
//            return;
//        }
        printParticipatorNameAndCard(participatorDto);
    }
}
