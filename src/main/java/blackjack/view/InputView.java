package blackjack.view;

import blackjack.model.Cards;
import blackjack.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<Player> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateMultipleInputs(input);
        return Arrays.stream(input.split(INPUT_DELIMITER))
                .map(name -> new Player(name, new Cards()))
                .toList();
    }

    private void validateMultipleInputs(String input) {
        if (input == null || input.isBlank() || input.endsWith(INPUT_DELIMITER)) {
            throw new IllegalArgumentException("입력값은 공백이거나 구분자로 끝날 수 없다.");
        }
    }
}
