package blackjack.domain.participant;

import java.util.regex.Pattern;

public class Name {

    private final String value;

    public Name(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}