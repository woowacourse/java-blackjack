package blackjack.view;

import blackjack.dto.PlayerDto;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] inputGamerNames() {
        try {
            OutputView.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String[] input = scanner.nextLine().split(",");
            for (int i = 0; i < input.length; i++) {
                validateGamerName(input[i]);
                input[i] = input[i].trim();
            }
            return input;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputGamerNames();
        }
    }

    private static void validateGamerName(String input) {
        if (input.length() < 1) {
            throw new IllegalArgumentException("[ERROR] 게임에 참여하는 사람의 이름은 1글자 이상입니다.");
        }
    }

    public static double inputBettingAmount(PlayerDto playerDto) {
        try {
            System.out.printf("\n%s의 배팅 금액은?\n", playerDto.getName());
            String input = scanner.nextLine();
            return Double.parseDouble(input);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputBettingAmount(playerDto);
        }
    }

    public static boolean inputHitOrStand(String gamerName) {
        try {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", gamerName);
            String input = scanner.nextLine();
            validateHitOrStand(input);
            return "y".equals(input);
        } catch (Exception e){
            OutputView.printExceptionMessage(e);
            return inputHitOrStand(gamerName);
        }
    }

    private static void validateHitOrStand(String input) {
        if (!(input.equals("y") || input.equals("n"))) {
            throw new IllegalArgumentException("[ERROR] y또는 n만 입력가능합니다.");
        }
    }
}
