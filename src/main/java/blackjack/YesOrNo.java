package blackjack;

public enum YesOrNo {
    YES("y", true),
    NO("n", false);

    String input;
    boolean answer;

    YesOrNo(String input, boolean answer) {
        this.input = input;
        this.answer = answer;
    }

    public static boolean isYes(YesOrNo yesOrNo) {
        return yesOrNo.equals(YesOrNo.YES);
    }
}
