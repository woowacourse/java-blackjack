package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PARTICIPANT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private final Scanner in;

    public InputView() {
        this.in = new Scanner(System.in);
    }

    public List<String> askParticipantNames() {
        System.out.println(ASK_PARTICIPANT_NAMES_MESSAGE);

        String input = in.nextLine();
        return List.of(input.split(",", -1));
    }

    public boolean askOneMoreCard(String name) {
        System.out.println(ASK_ONE_MORE_CARD_MESSAGE.formatted(name));

        String input = in.nextLine();
        if(input.equals("y")) {
            return true;
        }
        if(input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y/n 만 입력할 수 있습니다.");
    }
}
