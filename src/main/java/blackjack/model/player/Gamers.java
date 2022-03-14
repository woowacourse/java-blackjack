package blackjack.model.player;

import blackjack.model.card.CardDeck;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gamers {
    private static final int START_CARD_COUNT = 2;

    private final List<Player> values;

    public Gamers(final List<String> names) {
        this.values = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
    }

    public void giveCardsToGamer() {
        for (Player gamer : values) {
            giveCardsTo(gamer);
        }
    }

    private void giveCardsTo(final Player gamer) {
        CardDeck deck = CardDeck.getInstance();
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(deck.draw());
        }
    }

    public void hitOrStayToGamer(Predicate<String> predicate, Consumer<Player> consumer) {
        for (Player gamer : values) {
            hitOrStayTo(gamer, predicate, consumer);
        }
    }

    private void hitOrStayTo(final Player gamer, Predicate<String> predicate, Consumer<Player> consumer) {
        CardDeck deck = CardDeck.getInstance();
        while (canHit(gamer) && isHitSign(gamer, predicate)) {
            gamer.receive(deck.draw());
            consumer.accept(gamer);
        }
    }

    private boolean canHit(final Player gamer) {
        return !gamer.isBlackJack() && !gamer.isImpossibleHit();
    }

    private boolean isHitSign(Player gamer, Predicate<String> predicate) {
        return predicate.test(gamer.getName());
    }

    public List<Player> getValues() {
        return values;
    }
}

