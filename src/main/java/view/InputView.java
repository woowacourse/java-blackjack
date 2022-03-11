package view;

import java.util.Arrays;

public class InputView {

    private static final String NAME_OR_CARD_DELIMITER = ",";

    private InputView() {
    }

    static boolean hasNoDuplication(final String input) {
        final int beforeCount = getSplit(input).length;
        final int afterCount = (int) Arrays.stream(getSplit(input)).distinct().count();
        return beforeCount == afterCount;
    }

    private static String[] getSplit(final String input) {
        return input.split(NAME_OR_CARD_DELIMITER);
    }
}
