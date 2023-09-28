package model.player.dto;

import model.name.Name;

public class PlayerResponse {

    private final String name;
    private final int score;

    private PlayerResponse(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static PlayerResponse createDefault(final Name name, final int score) {
        return new PlayerResponse(name.getName(), score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
