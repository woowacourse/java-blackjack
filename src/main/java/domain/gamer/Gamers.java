package domain.gamer;

import domain.deck.Card;
import domain.deck.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamers {

    final List<Gamer> gamers;

    private Gamers(final List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public static Gamers of(final List<Player> players, final Gamer dealer) {
        final List<Gamer> values = new ArrayList<>(players);
        values.add(dealer);
        return new Gamers(values);
    }

    public void dealInitialCards(final Deck deck) {
        for (final Gamer gamer : gamers) {
            final Card firstCard = deck.drawCard();
            final Card secondCard = deck.drawCard();
            gamer.receiveInitialCards(List.of(firstCard, secondCard));
        }
    }

    public Map<Nickname, List<Card>> getCardsAtStartWithNickname() {
        return gamers.stream()
                .collect(Collectors.toMap(Gamer::getNickname, Gamer::getCards));
    }
}
