package view;

import controller.Answer;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] names = scanner.nextLine().split(",");
        return Arrays.stream(names).map(String::trim).toList();
    }

    public int readBettingAmount(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        try {
            return scanner.nextInt();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수 형식이 아닙니다.");
        }
    }

    public Answer readPlayerHit(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        return Answer.of(scanner.nextLine());
    }
}
