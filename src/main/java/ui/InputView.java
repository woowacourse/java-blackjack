package ui;

import domain.user.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import ui.dto.InputPlayerDTO;

public class InputView {
    private static final String HIT = "y";
    private static final String STAND = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<InputPlayerDTO> readPlayersInput() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> names = Arrays.stream(SCANNER.nextLine().split(",")).collect(Collectors.toList());
        List<InputPlayerDTO> inputPlayerDTOs = new ArrayList<>();
        for (String name : names) {
            inputPlayerDTOs.add(new InputPlayerDTO(name, readBettingAmount(name)));
        }
        return inputPlayerDTOs;
    }

    private static Integer readBettingAmount(String name) {
        String input = null;
        do {
            System.out.println("\n" + name + "의 베팅 금액은?");
            input = SCANNER.nextLine();
        } while (validateInputBetting(input));
        return Integer.parseInt(input);
    }

    private static boolean validateInputBetting(String input) {
        try {
            int amount = Integer.parseInt(input);
            validationMinusAmount(amount);
            new InputPlayerDTO("name", amount);
            return false;
        } catch (Exception e) {
            System.out.println("베팅 금액은 정수로 입력해야 합니다.");
            return true;
        }
    }

    private static void validationMinusAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 정수여야 합니다.");
        }
    }

    public static boolean readWhetherDrawCardOrNot(Player player) {
        String input = null;
        do {
            System.out.println(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            input = SCANNER.nextLine();
        } while (!validateIntentionInput(input));
        return HIT.equals(input);
    }

    private static boolean validateIntentionInput(String input) {
        if (HIT.equals(input) || STAND.equals(input)) {
            return true;
        }
        System.out.println("y또는 n으로 입력해주세요");
        return false;
    }
}
