package view;

import java.util.List;
import service.dto.InitGameDto;

public class OutputView {
    public static void printInit(InitGameDto initGameDto) {
        List<String> names = initGameDto.getNames();
        List<List<String>> cards = initGameDto.getCards();

        System.out.println(names.get(0)+ "와 " +  printPlayerNames(names) + "에게 2장을 나누었습니다.");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(names.get(i)+ ": " + printCards(cards.get(i)));
        }
    }

    private static String printCards(List<String> cards) {
        return String.join(", ", cards);
    }

    private static String printPlayerNames(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < names.size(); i++) {
            sb.append(names.get(i)).append(", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }
}
