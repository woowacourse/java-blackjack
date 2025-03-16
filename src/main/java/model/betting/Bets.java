package model.betting;

import java.util.ArrayList;
import java.util.List;
import model.participant.role.BetOwnable;
import model.participant.role.Bettable;

public class Bets {
    private final List<Bet> bets;

    public Bets() {
        this.bets = new ArrayList<>();
    }

    public void add(Bet bet) {
        this.bets.add(bet);
    }

    public void updateOwner(BetOwnable owner, BetOwnable newOwner) {
        Bet updatingBet = findByOwner(owner);
        this.bets.remove(updatingBet);
        this.bets.add(updatingBet.changeOwnerTo(newOwner));
    }

    public void updateBetAmount(Bettable better) {
        Bet bet = findByBetter(better);
        this.bets.remove(bet);
        this.bets.add(bet.increase(1.5));
    }

    public int calculateDealerRevenue() {
        return bets.stream()
                .mapToInt(Bet::calculateDealerRevenue)
                .sum();
    }

    public Bet findByBetter(Bettable better) {
        return bets.stream()
                .filter(bet -> bet.betterEquals(better))
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
