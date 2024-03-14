package view;

import domain.participant.PlayerName;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static view.FormatConverter.convertInputToDecision;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String inputString = scanner.nextLine();
        System.out.println();
        return Arrays.stream(inputString.split(",")).toList();
    }

    public static boolean inputDrawDecision(final PlayerName playerName) {
        System.out.println(playerName.value() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String inputString = scanner.nextLine();
        return convertInputToDecision(inputString);
    }

    public static String inputAmount(final String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        String inputBettingAmount = scanner.nextLine();
        System.out.println();
        return inputBettingAmount;
    }
}
