package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner in;

    public InputView() {
        this.in = new Scanner(System.in);
    }

    public List<String> inputParticipantNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = in.nextLine();
        return List.of(input.split(",", -1));
    }
}
