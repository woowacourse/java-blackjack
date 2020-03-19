package model.user.data;

import exception.PlayerNamesOverlapException;
import utils.StringUtils;

import java.util.*;

import static controller.BlackJackGame.COMMA;

public class PlayerNames implements Iterable<String> {
    private final Set<String> names;

    public PlayerNames(String input) {
        validate(input);
        names = new HashSet<>(Arrays.asList(StringUtils.trimString(input).split(COMMA)));
    }

    private void validate(String input) {
        StringUtils.validateNull(input);
        validateSplit(input);
        validateOverlap(input);
    }

    private void validateOverlap(String input) {
        List<String> names = Arrays.asList(StringUtils.trimString(input).split(COMMA));
        Set<String> noOverlapNames = new HashSet<>(names);
        if (names.size() != noOverlapNames.size()) {
            throw new PlayerNamesOverlapException("사용자 이름은 중복될 수 없습니다.");
        }
    }

    private void validateSplit(String input) {
        for (String name : input.split(COMMA)) {
            StringUtils.validateEmpty(name);
        }
    }

    public boolean contains(String name) {
        return names.contains(name);
    }

    @Override
    public Iterator<String> iterator() {
        return names.iterator();
    }
}