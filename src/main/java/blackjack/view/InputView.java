package blackjack.view;

import blackjack.domain.player.Player;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner console = new Scanner(System.in);
    private static final String YES = "y";
    private static final String NO = "n";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return parseNames(console.nextLine());
    }

    public String readBetAmount(String name) {
        System.out.println('\n' + name + "의 배팅 금액은?");
        return console.nextLine();
    }

    public boolean readOneMoreDealCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = console.nextLine();
        validateYesOrNo(input);

        return input.equals(YES);
    }

    private void validateYesOrNo(String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("y또는 n만 입력 가능합니다.");
        }
    }

    private static List<String> parseNames(String playerNamesInput) {
        return List.of(playerNamesInput.split(","));
    }
}
