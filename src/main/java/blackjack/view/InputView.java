package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator = new InputValidator();

    public List<String> readPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputPlayersName = scanner.nextLine();
        inputValidator.validateInput(inputPlayersName);
        List<String> playersName = Arrays.asList(inputPlayersName.split(DELIMITER, -1));
        return playersName;
    }

    public String readHitCommand() {
        String inputHitCommand = scanner.nextLine();
        inputValidator.validateInput(inputHitCommand);
        inputValidator.checkHitCommand(inputHitCommand);
        return inputHitCommand;
    }
}
