package model;

import java.util.*;

public class PlayerName {
    private static final String COMMA = ",";
    List<String> names;

    public PlayerName(String input) {
        validate(input);
        names = Arrays.asList(input.split(COMMA));
    }

    private void validate(String input) {
        validateNull(input);
        validateSplit(input);
    }

    private void validateSplit(String input) {
        String[] names = input.split(COMMA);
        for (String name : names) {
            validateEmpty(name);
        }
    }

    private void validateEmpty(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 한 글자 이상이어야 합니다.");
        }
    }

    private void validateNull(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Null 값 입니다.");
        }
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }
}
