package blackjack.domain.betting;

import java.util.HashMap;
import java.util.Map;

public class BettingRepository {

    // TODO: key는 String(PlayerName)이 좋을까? Player가 좋을까?
    private final Map<String, Integer> bettingRepository;

    public BettingRepository() {
        this.bettingRepository = new HashMap<>();
    }

    public void put(String playerName, Integer bettingAmount) {
        bettingRepository.put(playerName, bettingAmount);
    }

    public Integer findByName(String name) {
        return bettingRepository.get(name);
    }

}
