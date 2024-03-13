package repository;

import domain.Amount;
import domain.BetAmount;
import domain.Result;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//TODO 이런 형태 정말 괜찮은가?
// 쓸만한가, 장점은 무엇인가, 단점은 무엇인가, 대안은 무엇인가
public class BetAmountRepository {

    private final Map<Player, BetAmount> repository;

    public BetAmountRepository() {
        this(new HashMap<>());
    }

    protected BetAmountRepository(final Map<Player, BetAmount> repository) {
        this.repository = repository;
    }

    public void put(final Player player, final BetAmount betAmount) {
        repository.put(player, betAmount);
    }

    public Map<Player, Amount> calculateResult(final Dealer dealer) {
        final Map<Player, Amount> result = new HashMap<>();
        for (Entry<Player, BetAmount> entry : repository.entrySet()) {
            if (Result.WIN == entry.getKey().calculateResult(dealer)) {
                result.put(entry.getKey(), entry.getValue().winAmount());
            } else if (Result.TIE == entry.getKey().calculateResult(dealer)) {
                result.put(entry.getKey(), entry.getValue().tieAmount());
            } else {
                result.put(entry.getKey(), entry.getValue().loseAmount());
            }
        }
        return result;
    }
}
