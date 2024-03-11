package blackjack.view.format;

import blackjack.exception.NeedRetryException;

public enum CardRequestFormat {
    YES("y"),
    NO("n");

    private final String format;

    CardRequestFormat(final String format) {
        this.format = format;
    }

    public static boolean from(final String input) {
        if (YES.getFormat().equals(input)) {
            return true;
        }
        if (NO.getFormat().equals(input)) {
            return false;
        }
        throw new NeedRetryException("입력은 y또는 n으로 해주세요.");
    }

    public String getFormat() {
        return format;
    }
}
