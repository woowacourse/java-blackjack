package blackjack.view;

import blackjack.domain.Player;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine()
                .split(","));
    }

    public static boolean askAdditionalCard(Player player) {
        System.out.println(player.getName()+"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        String answer = scanner.nextLine();
        validateIsYesOrNo(answer);
        if(answer.equals("y")) {
            return true;
        }

        return false;
    }

    private static void validateIsYesOrNo(String answer) {
        if(!(answer.equals("y") || answer.equals("n"))) {
            throw new IllegalArgumentException("[ERROR] 답은 y, n 으로 해야 합니다.");
        }
    }
}
