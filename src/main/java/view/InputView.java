package view;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner scanner = new Scanner(System.in);

    public static String getParticipantNames() {
        System.out.println(ENTER_PLAYERS_NAME_MESSAGE);
        return readLine();
    }

    public static String inputNeedMoreCard(String participantName) {
        System.out.println(participantName + ASK_MORE_CARD_MESSAGE);
        return readLine();
    }

    private static String readLine() {
        return scanner.nextLine();
    }
}
