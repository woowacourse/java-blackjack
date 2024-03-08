package view;

import domain.participant.PlayerName;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String YES_STRING = "y";
    private static final String NO_STRING = "n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputString = scanner.nextLine();
        System.out.println();
        return Arrays.stream(inputString.split(",")).toList();
    }

    public static boolean inputDrawDecision(final PlayerName playerName) {
        System.out.println(playerName.value() + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String inputString = scanner.nextLine();
        return convertDecisionToBoolean(inputString);
    }

    private static boolean convertDecisionToBoolean(final String input) {
        if (input.equals(YES_STRING)) {
            return true;
        }

        if (input.equals(NO_STRING)) {
            return false;
        }

        throw new IllegalArgumentException("카드를 받을지 여부는 y/n만 입력할 수 있습니다.");
    }
}
