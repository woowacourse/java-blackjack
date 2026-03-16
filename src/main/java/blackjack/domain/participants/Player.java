package blackjack.domain.participants;

import blackjack.domain.game.GameResult;

public class Player extends Participant {
    private final Bet bet;

    public Player(Name name, Hand hand, Bet bet) {
        super(name, hand);
        this.bet = bet;
    }

    public Player(String name, Hand hand, Bet bet) {
        super(new Name(name), hand);
        this.bet = bet;
    }

    public static Player createEmptyHand(Name name, Bet bet) {
        return new Player(name, Hand.empty(), bet);
    }

    @Override
    public boolean canHit() {
        return !isBust();
    }

    public Profit calculateProfit(GameResult gameResult) {
        return bet.calculateProfit(gameResult);
    }
}
