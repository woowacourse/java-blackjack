package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> inputUsers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Parser.parseStringToList(scanner.next());
    }

    public String inputYesOrNo(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        return scanner.next();
    }

    public Long inputBettingMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        return Parser.parseLong(scanner.next());
    }

    public void close() {
        scanner.close();
    }
}
