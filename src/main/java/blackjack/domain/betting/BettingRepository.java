package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingRepository {

    // TODO: key는 String(PlayerName)이 좋을까? Player가 좋을까?
    private final Map<Player, BettingAmount> bettingRepository;

    public BettingRepository() {
        this.bettingRepository = new HashMap<>();
    }

    public void put(Player playerName, BettingAmount bettingAmount) {
        bettingRepository.put(playerName, bettingAmount);
    }

    public BettingAmount findByPlayer(Player player) {
        return bettingRepository.get(player);
    }

}
