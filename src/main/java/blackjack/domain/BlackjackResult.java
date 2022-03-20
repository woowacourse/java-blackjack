package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum BlackjackResult {
    BLACKJACK_DRAW((dealer, player) -> (State.BLACKJACK.equals(dealer.state()) &&
                                        State.BLACKJACK.equals(player.state())),
            (receipt -> Receipt.empty())),

    BLACKJACK_DEALER_WIN((dealer, player) -> (State.BLACKJACK.equals(dealer.state())),
            (Receipt::opposite)),

    BLACKJACK_PLAYER_WIN((dealer, player) -> (State.BLACKJACK.equals(player.state())),
            (Receipt::blackjack)),

    BUST_DRAW((dealer, player) -> (State.BUST.equals(dealer.state()) && State.BUST.equals(player.state())),
            (receipt -> Receipt.empty())),

    BUST_PLAYER((dealer, player) -> (State.BUST.equals(player.state())),
            (Receipt::opposite)),

    BUST_DEALER((dealer, player) -> (State.BUST.equals(dealer.state())),
            (receipt -> receipt)),

    PLAYER_WIN((dealer, player) -> (State.STAY.equals(dealer.state()) &&
                                 State.STAY.equals(player.state()) &&
                                 player.score() > dealer.score()),
            (receipt -> receipt)),

    DRAW((dealer, player) -> (State.STAY.equals(dealer.state()) &&
                                 State.STAY.equals(player.state()) &&
                                 player.score() == dealer.score()),
            (receipt -> Receipt.empty())),

    PLAYER_LOSE((dealer, player) -> (State.STAY.equals(dealer.state()) &&
                                     State.STAY.equals(player.state()) &&
                                     player.score() < dealer.score()),
            (Receipt::opposite));

    private BiPredicate<Dealer, Player> condition;
    private Function<Receipt, Receipt> settle;

    BlackjackResult(BiPredicate<Dealer, Player> condition, Function<Receipt, Receipt> settle) {
        this.condition = condition;
        this.settle = settle;
    }

    public Receipt settle(Receipt receipt) {
        return settle.apply(receipt);
    }

    public static BlackjackResult of(Dealer dealer, Player player) {
        return Arrays.stream(BlackjackResult.values())
                .filter(result -> result.condition.test(dealer, player))
                .findFirst()
                .orElseThrow();
    }
}
