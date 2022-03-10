package view;

import java.util.List;
import java.util.stream.Collectors;
import dto.InitGameDto;
import dto.ParticipatorDto;

public class OutputView {
    public static void printInit(InitGameDto initGameDto) {
        List<ParticipatorDto> participatorDtos = initGameDto.getParticipatorDtos();
        List<String> names = getNames(participatorDtos);
        System.out.println(names.get(0)+ "와 " +  convertPlayerInLine(names) + "에게 2장을 나누었습니다.");
        for (int i = 0; i < participatorDtos.size(); i++) {
            printParticipatorNameAndCard(participatorDtos.get(i));
        }
    }

    private static List<String> getNames(List<ParticipatorDto> participators) {
        return participators.stream()
                .map(ParticipatorDto::getName)
                .collect(Collectors.toList());
    }

    private static void printParticipatorNameAndCard(ParticipatorDto participatorDto) {
        System.out.println(participatorDto.getName()+ ": " + convertCardsInLine(participatorDto.getCards()));
    }

    private static String convertCardsInLine(List<String> cards) {
        return String.join(", ", cards);
    }

    private static String convertPlayerInLine(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < names.size(); i++) {
            sb.append(names.get(i)).append(", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
}
