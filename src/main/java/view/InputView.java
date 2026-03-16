package view;

import domain.ExceptionMessage;
import domain.participant.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readLine();
        String[] split = input.split(",");
        return List.of(split);
    }

    public String readHitStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = readLine();
        if (!(input.equals("y") || input.equals("n"))) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_HIT_STAND_RESPONSE.getMessage());
        }
        return input;
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
