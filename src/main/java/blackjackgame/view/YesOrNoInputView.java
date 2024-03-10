package blackjackgame.view;

public class YesOrNoInputView {

    private YesOrNoInputView() {
    }

    public static Boolean getYNAsBoolean() {
        String input = Console.getInputFromConsole();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
    }
}
