package view;

import controller.AnswerCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final int MAX_PLAYER = 7;

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = readInput();
        final String[] parsedInput = input.split(",");
        return Arrays.stream(parsedInput).map(String::trim).toList();
    }

    public AnswerCommand readAnswer(final String playerName) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));
        final String input = readInput();
        return AnswerCommand.findByAnswer(input);
    }

    public int readBet(final String playerName){
        System.out.println(playerName + "의 배팅 금액은?");
        final String input = readInput();
        validateBetAmount(input);
        return Integer.parseInt(input);
    }

    private void validateBetAmount(String betAmountInput){
        try{
            int betAmount = Integer.parseInt(betAmountInput);
            validateBetAmountRange(betAmount);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 가능합니다.");
        }
    }

    private void validateBetAmountRange(int betAmount){
        if(betAmount <= 0){
            throw new IllegalArgumentException("[ERROR] 배팅 금액이 올바르지 않습니다.");
        }
    }

    private String readInput() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
