package blackjackgame.view;

import java.util.List;
import java.util.Scanner;
import blackjackgame.model.participants.player.Player;
import blackjackgame.model.participants.player.Players;

public class InputView {

    private static final String HIT_WORD = "y";
    private static final String NO_HIT_WORD = "n";

    private final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public Players requestPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNames = splitName(scanner.nextLine());
        playerNames = playerNames.stream()
                .map(this::removeBlank)
                .toList();
        return Players.from(playerNames);
    }

    public int requestBettingMoney(Player player) {
        System.out.println(player.getName() + "의 배팅 금액은?");
        return scanner.nextInt();
    }

    public boolean requestHitAnswer(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = removeBlank(scanner.nextLine());
        hitAnswerValidate(answer);
        return answer.equals(HIT_WORD);
    }

    private void hitAnswerValidate(String answer) {
        if (!HIT_WORD.equals(answer) && !NO_HIT_WORD.equals(answer)) {
            throw new IllegalArgumentException("y 와 n 이외의 문자를 입력할 수 없습니다");
        }
    }

    private List<String> splitName(String input) {
        return List.of(input.split(","));
    }

    private String removeBlank(String input) {
        return input.trim();
    }
}
