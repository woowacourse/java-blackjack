package view;

import java.util.Arrays;

public enum YesNo {
    YES("y", true),
    NO("n", false);

    private final String value;
    private final boolean yesno;

    YesNo(String value, boolean yesno) {
        this.value = value;
        this.yesno = yesno;
    }

    public static YesNo of(String yesno) {
        return Arrays.stream(YesNo.values())
                .filter(eachYesNo -> yesno.equals(eachYesNo.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y와 n만 입력해주세요."));
    }

    public boolean getYesno() {
        return yesno;
    }
}
