package blackjack.domain.participant;

import utils.StringUtils;

public class Name {
    private static final String NAME_ERROR_EXCEPTION_MESSAGE = "공백이 들어와서는 안됩니다.";
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(NAME_ERROR_EXCEPTION_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
