package view;

import domain.pariticipant.BettingAmount;
import domain.pariticipant.Name;
import java.util.List;
import java.util.Scanner;

import static exception.ErrorMessage.*;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public List<Name> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        validateIsBlank(input);

        return InputParser.parsePlayers(input);
    }

    public boolean readHitOrStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();

        validateIsBlank(input);
        input = input.trim();

        if (!input.matches("[y|n]")) {
            throw new IllegalArgumentException(INVALID_HIT_STAND_INPUT_ERROR.getMessage());
        }
        return "y".equals(input);
    }
    

    private static void validateIsBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR.getMessage());
        }
    }

    public BettingAmount readBettingAmount(String name) {
        System.out.printf("\n%s의 베팅 금액은?\n", name);

        int bettingAmount;
        try {
            bettingAmount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT_FORMAT_ERROR.getMessage());
        }

        return new BettingAmount(bettingAmount);
    }
}
