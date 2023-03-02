package view;

import java.util.Scanner;

public class InputView {
    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final Scanner scanner = new Scanner(System.in);

    public String readPlayerNames(){
        System.out.println(READ_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }
    
}
