package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static double inputBattingMoney(String playerName){
        System.out.printf("%s의 배팅금액을 입력하세요\n",playerName);
        try{
            String battingMoney = SCANNER.nextLine();
            validateNumberFormat(battingMoney);
            return Double.parseDouble(SCANNER.nextLine());
        }catch (RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
            return inputBattingMoney(playerName);
        }
    }

    private static void validateNumberFormat(String battingMoney){
        try{
            Double.parseDouble(battingMoney);
        }catch (NumberFormatException e){
            throw new NullPointerException("숫자만 입력 가능합니다.");
        }
    }

    public static String inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String playerName = SCANNER.nextLine();
            validateNullOrEmpty(playerName);
            return playerName;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerName();
        }
    }

    private static void validateNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static String inputAnswer(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)\n", name);
        try {
            String answer = SCANNER.nextLine();
            validateNullOrEmpty(answer);
            return answer;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputAnswer(name);
        }
    }

}

