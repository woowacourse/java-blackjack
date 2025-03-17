package view;

public enum YesOrNo {
    YES, NO;

    public static YesOrNo from(String input) {
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
            return YES;
        }
        if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
            return NO;
        }
        throw new IllegalArgumentException("Y(YES) 혹은 N(NO)를 입력해주세요.");
    }
}
