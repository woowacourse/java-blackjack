package blackjack.view;

import blackjack.dto.HitRequest;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine()
                .split(",", -1));
    }

    public static HitRequest inputHitRequest(String playerName) {
        System.out.printf("%n%s는(은) 한 장의 카드를 더 받으시겠습니까? (예는 y, 아니오는 n)%n", playerName);
        return HitRequest.find(scanner.nextLine());
    }

    public static int inputBetMoney(String name) {
        try {
            System.out.printf("%s의 베팅 금액은?%n", name);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 베팅 금액은 숫자여야 합니다.");
            return inputBetMoney(name);
        }
    }
}
