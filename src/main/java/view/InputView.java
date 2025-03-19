package view;

import domain.Player;
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

    public AnswerType requestAdditionalCard(String playerName) {
        System.out.printf("\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        String rawAnswer = readLine();
        return inputParser.parseAnswerType(rawAnswer);
    }

    public int requestBettingMoney(String playerName) {
        System.out.printf("%s의 배팅 금액은?", playerName);
        return inputParser.parseToInt(readLine());
    }
}
