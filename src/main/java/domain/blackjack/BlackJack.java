package domain.blackjack;

import domain.participant.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {

    private final Players players;
    private final Dealer dealer;

    public BlackJack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void beginDealing(BiConsumer<Players, Dealer> beginBlackJack) {
        players.beginDealing(dealer);
        beginBlackJack.accept(players, dealer);
    }

    public void play(Function<Name, HitOption> isHitOption, Consumer<Player> printPlayerHands) {
        players.playersHit(isHitOption, printPlayerHands, dealer);
    }

    public int dealerHit() {
        int count = 0;
        while (dealer.canHit()) {
            dealer.receiveCard(dealer.draw());
            count++;
        }
        return count;
    }
}
