package domain;

public enum AdditionalDrawStatus {
    DRAW,
    PASS;

    public static boolean isDrawable(AdditionalDrawStatus additionalDrawStatus) {
        return additionalDrawStatus == AdditionalDrawStatus.DRAW;
    }
}
