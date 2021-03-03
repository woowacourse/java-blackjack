package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_PARTICIPANT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static List<String> inputParticipantNames() {
        System.out.println(INPUT_PARTICIPANT_NAMES_MESSAGE);
        String input = SCANNER.nextLine();
        String[] participantNames = input.split(DELIMITER);
        return Arrays.asList(participantNames);
    }
}
