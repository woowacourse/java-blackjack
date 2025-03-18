package model.betting;

import java.util.ArrayList;
import java.util.List;
import model.participant.role.BetOwnable;
import model.participant.role.Bettable;

public final class Bets {
    private final List<Bet> bets;

    public Bets() {
        this.bets = new ArrayList<>();
    }

    public void add(final Bet bet) {
        this.bets.add(bet);
    }

    public void updateOwner(final BetOwnable owner, final BetOwnable newOwner) {
        Bet updatingBet = findByOwner(owner);
        this.bets.remove(updatingBet);
        this.bets.add(updatingBet.changeOwnerTo(newOwner));
    }

    public void updateBetAmount(final Bettable better) {
        Bet bet = findByBetter(better);
        this.bets.remove(bet);
        this.bets.add(bet.increase(IncreasingRate.whenBlackjackWin()));
    }

    public int calculateDealerRevenue() {
        return bets.stream()
                .mapToInt(bet -> -bet.calculateBetterRevenue())
                .sum();
    }

    public Bet findByBetter(final Bettable better) {
        return bets.stream()
                .filter(bet -> bet.betterEquals(better))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당하는 플레이어를 찾을 수 없습니다."));
    }

    private Bet findByOwner(final BetOwnable owner) {
        return bets.stream()
                .filter(bet -> bet.getOwner().equals(owner))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("플레이어의 배팅 금액이 저장되지 않았습니다."));
    }
}
