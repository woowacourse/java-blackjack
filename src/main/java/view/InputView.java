package view;

import controller.AnswerCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = readInput();
        return Arrays.stream(input.split(",")).map(String::trim).toList();
    }

    public AnswerCommand readAnswer(final String playerName) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));
        final String input = readInput();
        return AnswerCommand.findByAnswer(input);
    }

    private String readInput() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
