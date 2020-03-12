package view;

import java.util.Arrays;

public enum YesNo {
    YES("y", true),
    NO("n", false);

    private final String value;
    private final boolean yesNo;

    YesNo(String value, boolean yesNo) {
        this.value = value;
        this.yesNo = yesNo;
    }

    public static YesNo of(String yesno) {
        return Arrays.stream(YesNo.values())
                .filter(eachYesNo -> yesno.equals(eachYesNo.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y와 n만 입력해주세요."));
    }

    public boolean getYesNo() {
        return yesNo;
    }
}
