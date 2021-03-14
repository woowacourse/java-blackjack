package blackjack.view;

import blackjack.domain.Participant;
import blackjack.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final int MIN_INPUT_BET_AMOUNT = 1;

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.println();
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        String[] playerNames = input.split(DELIMITER);
        printEmptyLine();
        return Arrays.asList(playerNames);
    }

    public static double inputBetAmount(Player player) {
        System.out.println(player.getName() + "의 베팅 금액은?");
        String input = SCANNER.nextLine();
        printEmptyLine();
        double betAmount = Integer.parseInt(input);
        validateInputBetAmount(betAmount);
        return betAmount;
    }

    private static void validateInputBetAmount(double betAmount) {
        if (betAmount < MIN_INPUT_BET_AMOUNT) {
            throw new IllegalArgumentException("입력할 베팅 금액은 0이 넘어야 합니다.");
        }
    }

    public static String inputAnswerToAdditionalCardQuestion(Participant participant) {
        System.out.println(participant.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
