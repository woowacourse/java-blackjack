package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String nameInput = scanner.nextLine();
        return Stream.of(nameInput.split(",", -1))
            .map(String::trim)
            .collect(Collectors.toUnmodifiableList());
    }

    public int askPlayerBettingAmount(final String playerName) {
        System.out.println();
        System.out.println(playerName + "의 배팅 금액은?");
        String bettingAmountInput = scanner.nextLine();
        validateBettingAmountInput(bettingAmountInput);
        return Integer.parseInt(bettingAmountInput);
    }

    private void validateBettingAmountInput(String bettingAmountInput) {
        try {
            Integer.parseInt(bettingAmountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 양의 정수만 입력할 수 있습니다.");
        }
    }

    public boolean askHitCommand(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String command = scanner.nextLine();
        return HitCommand.from(command);
    }
}
