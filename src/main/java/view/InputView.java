package view;

import java.util.Scanner;

public class InputView {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static String readAnswerForMoreCard(String participantName) {
        System.out.println(participantName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine();
    }
    
    private static String readLine() {
        return scanner.nextLine();
    }
    
    public static String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }
}
