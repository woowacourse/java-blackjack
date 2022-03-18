package blackjack.domain.participant;

import blackjack.domain.money.Money;

public class ParticipantGroup {

    private final Dealer dealer;
    private final Players players;

    public ParticipantGroup(Dealer dealer) {
        this.dealer = dealer;
        this.players = new Players();
    }

    public static ParticipantGroup of(Dealer dealer) {
        return new ParticipantGroup(dealer);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public Money calculateDealerProfit() {
        Money totalPlayerProfit = players.calculateTotalProfit(dealer);
        return Money.createAsNegative(totalPlayerProfit);
    }

    @Override
    public String toString() {
        return "ParticipantGroup{" +
                "dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
