package blackjack.view;

import blackjack.domain.game.Players;

public class InputView {

    private InputView() {
    }

    public static String inputPlayerNames(final Enterable enterable) {
        String input = enterable.enter();

        validateNullOrEmpty(input);
        for (String name : input.split(Players.DELIMITER)) {
            validateNullOrEmpty(name.trim());
        }
        return input;
    }

    public static String inputDrawingAnswer(final Enterable enterable) {
        String input = enterable.enter();

        validateNullOrEmpty(input);
        return input;
    }

    private static void validateNullOrEmpty(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }
}
