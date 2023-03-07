package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

import static blackjack.util.ExceptionTemplate.repeatAndPrintCause;

public class BlackJackManager {
    private final Deck deck;
    private final BlackJackParticipants participants;

    public BlackJackManager(final Deck deck, final List<String> names) {
        this.deck = deck;
        this.participants = new BlackJackParticipants(this.deck, names);
    }

    public void hitByPlayer(final Predicate<String> checkHitCondition, final Consumer<Player> printPlayerCards) {
        participants.getPlayers()
                .forEach(player -> hitBy(player, checkHitCondition, printPlayerCards));
    }

    public void hitByDealer(final IntConsumer printHitCount) {
        final Dealer dealer = participants.getDealer();
        int hitCount = 0;
        while (dealer.isHittable()) {
            dealer.hit(deck.draw());
            hitCount++;
        }

        if (hitCount > 0) {
            printHitCount.accept(hitCount);
        }
    }

    private void hitBy(
            final Player player,
            final Predicate<String> checkHitCondition,
            final Consumer<Player> printPlayerCards
    ) {
        final String name = player.getName().getValue();
        while (player.isHittable() && repeatAndPrintCause(() -> checkHitCondition.test(name))) {
            player.hit(deck.draw());
            printPlayerCards.accept(player);
        }
        if (player.isHittable()) {
            printPlayerCards.accept(player);
        }
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public BlackJackResults getBlackJackResults() {
        return participants.createBlackJackResults();
    }
}
