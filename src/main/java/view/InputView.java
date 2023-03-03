package view;

import domain.participant.Participant;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER_USERNAME = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String names = scanner.nextLine();
        return List.of(names.split(DELIMITER_USERNAME));
    }

    public static String readHit(Participant playerName) {
        System.out.println(playerName.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
