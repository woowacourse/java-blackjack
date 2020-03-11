package model;

import utils.StringUtils;

import java.util.*;

public class PlayerName {
    private static final String COMMA = ",";
    List<String> names;

    public PlayerName(String input) {
        validate(input);
        names = Arrays.asList(input.split(COMMA));
    }

    private void validate(String input) {
        StringUtils.validateNull(input);
        validateSplit(input);
    }

    private void validateSplit(String input) {
        String[] names = input.split(COMMA);
        for (String name : names) {
            StringUtils.validateEmpty(name);
        }
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }
}
