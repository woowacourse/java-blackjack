package blackjack.view.utils;

import java.util.List;

public enum Delimiter {

    COMMA(","),
    SPACE(" "),
    COLON(":");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public List<String> splitWith(final String value) {
        final int limitForSplitAllElement = -1;
        return List.of(value.split(this.unit, limitForSplitAllElement));
    }

    public String joinWith(final List<String> values) {
        return String.join(this.unit + SPACE.unit, values);
    }

    public String joinWith(final String... values) {
        return String.join(this.unit + SPACE.unit, values);
    }

}