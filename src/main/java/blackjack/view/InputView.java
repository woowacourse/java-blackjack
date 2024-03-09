package blackjack.view;


import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DRAW_EXTRA_CARD = "y";
    private static final String STOP_DRAW_CARD = "n";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String line = scanner.nextLine();
        return List.of(line.split(","));
    }

    public boolean inputDrawChoice(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까? (y/n)");
        String choice = scanner.nextLine();
        if (DRAW_EXTRA_CARD.equals(choice)) {
            return true;
        }
        if (STOP_DRAW_CARD.equals(choice)) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n만 선택할 수 있습니다.");
    }
}
