package dto;

import participants.Name;

public class BettingResultDto {
    private final Name name;
    private final int reward;

    public BettingResultDto(Name name, int reward) {
        this.name = name;
        this.reward = reward;
    }

    public Name getName() {
        return name;
    }

    public int getReward() {
        return reward;
    }
}
