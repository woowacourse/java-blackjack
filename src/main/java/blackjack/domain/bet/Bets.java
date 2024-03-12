package blackjack.domain.bet;

import java.util.List;

public class Bets {

    private final List<Bet> bets;

    public Bets(List<Bet> bets) {
        this.bets = bets;
    }

    public Bet getBetByPlayerName(String playerName) {
        return bets.stream()
                .filter(bet -> bet.getPlayerName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[INTERNAL_ERROR] 해당 이름으로 등록된 베팅 정보를 찾을 수 없습니다"));
    }
}
