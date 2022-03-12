package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
    private static final int START_CARD_COUNT = 2;

    private final List<Player> values;
    private int gamerOrder = 0;

    private Gamers(List<Gamer> gamers) {
        this.values = List.copyOf(gamers);
    }

    public static Gamers from(List<String> names) {
        List<Gamer> gamers = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
        return new Gamers(gamers);
    }

    public void giveCardsBy(CardDeck cardDeck) {
        for (Player gamer : values) {
            giveCardsToGamerBy(cardDeck, gamer);
        }
    }

    private void giveCardsToGamerBy(CardDeck cardDeck, Player gamer) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(cardDeck.draw());
        }
    }

    public boolean hasNeverHitOrStayGamer() {
        return values.size() > gamerOrder;
    }

    public void hitCurrentGamerBy(CardDeck cardDeck) {
        values.get(gamerOrder).hit(cardDeck.draw());
    }

    public List<Player> getValues() {
        return values;
    }

    public Player getCurrentValue() {
        return values.get(gamerOrder);
    }

    public boolean isCurrentGamerCanNotHit() {
        return values.get(gamerOrder).isImpossibleHit();
    }

    public void readyNextGamer() {
        gamerOrder += 1;
    }
}
