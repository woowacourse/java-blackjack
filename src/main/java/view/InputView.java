package view;

import java.util.Scanner;

public class InputView {
    
    public static final String ILLEGAL_ANSWER_MESSAGE = "잘못된 입력입니다.";
    public static final String CONTINUE = "y";
    public static final String STOP = "n";
    private static final Scanner scanner = new Scanner(System.in);
    
    public static boolean readAnswerForMoreCard(String participantName) {
        System.out.println(participantName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
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
    
    private static String readLine() {
        return scanner.nextLine();
    }
    
    public static String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = readLine();
        System.out.println();
        return names;
    }
}
