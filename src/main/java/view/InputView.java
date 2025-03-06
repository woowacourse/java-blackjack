package view;

import java.util.List;
import java.util.Scanner;
import view.support.InputParser;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawPlayerNames = readLine();
        return inputParser.parsePlayerNames(rawPlayerNames);
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
