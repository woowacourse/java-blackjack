package view;

import java.util.List;
import java.util.Scanner;

import participants.Name;
import utils.StringUtils;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine().split(","));
    }

    public String readHitCommand(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) %n", name.getValue());
        return scanner.nextLine();
    }

    public int readBetAmount(String name) {
        System.out.printf("%n%s의 배팅 금액은?", name);
        return StringUtils.parseInt(scanner.nextLine());
    }
}
