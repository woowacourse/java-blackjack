package domain;

import java.util.Map;

import domain.player.User;

public class UserResult {
    private final Map<User, Result> results;

    public UserResult(Map<User, Result> results) {
        this.results = results;
    }
}
