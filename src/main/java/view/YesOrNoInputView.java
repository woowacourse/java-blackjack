package view;

public class YesOrNoInputView {
    public static Boolean getYNAsBoolean(String playerName) {
        String inputGuideOutput = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n".formatted(playerName);
        Console.print(inputGuideOutput);
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
