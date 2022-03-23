package domain.participant;

import java.util.List;
import java.util.stream.Collectors;
import utils.ExceptionMessages;

public final class Name {

    private final String value;

    private Name(String value) {
        String name = value.trim();
        validateEmpty(name);
        this.value = name;
    }

    public static Name from(String nameValue) {
        return new Name(nameValue);
    }

    public static List<Name> from(List<String> nameValues) {
        return nameValues.stream()
            .map(Name::new)
            .collect(Collectors.toList());
    }

    private void validateEmpty(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.EMPTY_NAME_ERROR);
        }
    }

    public String getValue() {
        return value;
    }
}
