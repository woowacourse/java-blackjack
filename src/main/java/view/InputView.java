package view;

import java.util.Scanner;

public class InputView {
    
    public static final String ILLEGAL_ANSWER_MESSAGE = "잘못된 입력입니다. y 또는 n을 입력해주세요.";
    public static final String CONTINUE = "y";
    public static final String STOP = "n";
    public static final String PARTICIPANTS_NAME_REQUEST_PROMPT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String MORE_CARD_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String BETTING_MONEY_REQUEST_MESSAGE = "%s의 배팅 금액은?";
    public static final String NOT_NUMBER_ERROR_MESSAGE = "잘못된 입력입니다. 숫자만 입력 가능합니다.";
    private static final Scanner scanner = new Scanner(System.in);
    
    public static String readPlayerNames() {
        System.out.println(PARTICIPANTS_NAME_REQUEST_PROMPT_MESSAGE);
        String names = readLine();
        System.out.println();
        return names;
    }
    
    private static String readLine() {
        return scanner.nextLine();
    }
    
    public static int readBet(String participantName) {
        System.out.printf(BETTING_MONEY_REQUEST_MESSAGE, participantName);
        System.out.println();
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }
    
    public static boolean readAnswerForMoreCard(String participantName) {
        System.out.printf(MORE_CARD_REQUEST_MESSAGE, participantName);
        System.out.println();
        return getAnswer(readLine());
    }
    
    private static boolean getAnswer(String answer) {
        if (answer.equals(CONTINUE)) {
            return true;
        }
        if (answer.equals(STOP)) {
            return false;
        }
        throw new IllegalArgumentException(ILLEGAL_ANSWER_MESSAGE);
    }
    
}
