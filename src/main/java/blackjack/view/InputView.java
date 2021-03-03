package blackjack.view;

import blackjack.domain.Participant;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        String input = SCANNER.nextLine();
        String[] playerNames = input.split(DELIMITER);
        return Arrays.asList(playerNames);
    }

    public static String inputAnswerToAdditionalCardQuestion(Participant participant) {
        System.out.println(participant.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }
}
