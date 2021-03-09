package blackjack.view;

import blackjack.domain.participant.Participant;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYER_CHOICE_FOR_MORE_CARD = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_BETTING_MONEY = "%s의 배팅 금액은?";

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        String input = SCANNER.nextLine();
        String[] playerNames = input.split(DELIMITER);
        return Arrays.asList(playerNames);
    }

    public static List<Integer> inputAllPlayersBettingMoney(List<String> playerNames) {
        return playerNames.stream()
                .map(InputView::inputEachPlayerBettingMoney)
                .collect(Collectors.toList());
    }

    private static int inputEachPlayerBettingMoney(String playerName) {
        printNewLine();
        System.out.printf(INPUT_BETTING_MONEY, playerName);
        printNewLine();
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static String inputAnswerForAdditionalCardDraw(Participant participant) {
        System.out.printf(INPUT_PLAYER_CHOICE_FOR_MORE_CARD, participant.getName());
        printNewLine();
        return SCANNER.nextLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
