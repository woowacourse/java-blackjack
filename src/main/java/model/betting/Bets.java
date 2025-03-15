package model.betting;

import java.util.ArrayList;
import java.util.List;
import model.participant.Dealer;
import model.participant.Player;

public class Bets {
    private final List<Bet> bets;

    public Bets() {
        this.bets = new ArrayList<>();
    }

    public void add(Bet bet) {
        this.bets.add(bet);
    }

    public void updateOwner(Player player, Dealer dealer) { //TODO : 매개변수 추상화
        Bet updatingBet = findByOwner(player);
        this.bets.remove(updatingBet);
        this.bets.add(updatingBet.changeOwnerTo(dealer));
    }

    public void updateBetAmount(Player player) {
        Bet bet = findByBetter(player);
        this.bets.remove(bet);
        this.bets.add(bet.increase(1.5));
    }

    public int calculateDealerRevenue() {
        return bets.stream()
                .mapToInt(Bet::calculateDealerRevenue)
                .sum();
    }

    public Bet findByBetter(Player player) {
        return bets.stream()
                .filter(bet -> bet.betterEquals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당하는 플레이어를 찾을 수 없습니다."));
    }

    private Bet findByOwner(Object owner) {
        return bets.stream()
                .filter(bet -> bet.getOwner().equals(owner))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("플레이어의 배팅 금액이 저장되지 않았습니다."));
    }
}
