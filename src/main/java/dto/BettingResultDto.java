package dto;

import betting.Reward;
import participants.Name;
import participants.Player;

public class BettingResultDto {
    private final Name name;
    private final Reward reward;

    public BettingResultDto(Name name, Reward reward) {
        this.name = name;
        this.reward = reward;
    }

    public static BettingResultDto from(Player player, Reward reward) {
        return new BettingResultDto(player.getName(), reward);
    }

    public Name getName() {
        return name;
    }

    public Reward getReward() {
        return reward;
    }
}
