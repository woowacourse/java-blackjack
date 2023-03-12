package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.LinkedHashMap;

public class RewardDTO {

    private final LinkedHashMap<String, Double> rewardsDTO;

    private RewardDTO(LinkedHashMap<String, Double> rewardsDTO) {
        this.rewardsDTO = rewardsDTO;
    }

    public static RewardDTO of(Rewards rewards) {
        LinkedHashMap<String, Double> rewardsDTO = new LinkedHashMap<>();
        rewardsDTO.put(Dealer.DEALER_NAME, rewards.removeDealerResult());
        for (User user : rewards.getRewards().keySet()) {
            rewardsDTO.put(user.getName(), rewards.getRewards().get(user));
        }
        return new RewardDTO(rewardsDTO);
    }

    public LinkedHashMap<String, Double> getPrizeDTO() {
        return rewardsDTO;
    }
}
