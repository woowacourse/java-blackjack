package blackjack.model.player;

import blackjack.model.card.CardDeck;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Participants {
    private final Players players;
    private final Participant dealer;

    public Participants(final List<String> names) {
        checkDuplicateIn(names);
        this.players = Players.from(names);
        this.dealer = new Dealer();
    }

    private Participants(final Players players, final Participant dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private void checkDuplicateIn(final List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
        }
    }

    public Participants drawCardsBy(final CardDeck cardDeck) {
        return new Participants(players.drawCardsBy(cardDeck), dealer.drawCardsBy(cardDeck));
    }

    public void hitOrStayBy(CardDeck cardDeck, Predicate<String> predicate, BiConsumer<String, List<String>> consumer) {
        players.hitOrStayBy(cardDeck, predicate, consumer);
        dealer.hitOrStayBy(cardDeck, predicate, consumer);
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players.getValues();
    }
}
