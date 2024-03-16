package dto;

import model.game.ResultStatus;

public class PlayerResult {

    private final String name;
    private final ResultStatus status;

    public PlayerResult(String name, ResultStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public ResultStatus getStatus() {
        return status;
    }
}
