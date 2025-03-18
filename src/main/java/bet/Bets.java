package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Participant;

public class Bets {
    private final Map<Participant, Bet> bets;

    public Bets() {
        bets = new LinkedHashMap<>();
    }

    public void addBet(Participant participant, int amount) {
        bets.put(participant, new Bet(amount));
    }

    public Bet getBet(Participant participant) {
        if (!bets.containsKey(participant)) {
            throw new IllegalStateException("논리적으로 플레이어의 베팅이 존재하지 않을 경우의 수가 없습니다.");
        }

        return bets.get(participant);
    }

    public int getAmount(Participant participant) {
        return getBet(participant).getAmount();
    }
}
