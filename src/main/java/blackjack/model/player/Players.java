package blackjack.model.player;

import blackjack.model.card.CardDeck;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Players {
    private List<Participant> values;

    public Players(final List<String> names) {
        this.values = names.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCardsBy(final CardDeck cardDeck) {
        this.values = values.stream()
                .map(player -> player.drawCardsBy(cardDeck))
                .collect(Collectors.toUnmodifiableList());
    }

    public void hitOrStayToGamer(Predicate<String> predicate, Consumer<Participant> consumer) {
        for (Participant gamer : values) {
            hitOrStayTo(gamer, predicate, consumer);
        }
    }

    private void hitOrStayTo(final Participant gamer, Predicate<String> predicate, Consumer<Participant> consumer) {
        CardDeck deck = CardDeck.getInstance();
        while (canHit(gamer) && isHitSign(gamer, predicate)) {
            gamer.receive(deck.draw());
            consumer.accept(gamer);
        }
    }

    private boolean canHit(final Participant gamer) {
        return !gamer.isBlackJack() && !gamer.isImpossibleHit();
    }

    private boolean isHitSign(Participant gamer, Predicate<String> predicate) {
        return predicate.test(gamer.getName());
    }

    public List<Participant> getValues() {
        return values;
    }
}

