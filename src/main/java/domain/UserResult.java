package domain;

import domain.player.User;

import java.util.Map;

public class UserResult {
    private final Map<User, Result> results;

    public UserResult(Map<User, Result> results) {
        this.results = results;
    }
}
