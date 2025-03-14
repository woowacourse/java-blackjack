package domain.gamer;

import domain.deck.Card;
import domain.deck.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamers {

    private static final String DEALER_NICKNAME = "딜러";
    final List<Gamer> gamers;

    private Gamers(final List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public static Gamers of(final Players players, final Gamer dealer) {
        final List<Gamer> gamerGroup = new ArrayList<>(players.getPlayers());
        gamerGroup.add(dealer);
        return new Gamers(sortGamers(gamerGroup));
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

    private static List<Gamer> sortGamers(final List<Gamer> gamers) {
        gamers.sort(Gamers::compareGamers);
        return gamers;
    }

    private static int compareGamers(final Gamer g1, final Gamer g2) {
        final boolean g1IsDealer = isDealer(g1);
        final boolean g2IsDealer = isDealer(g2);

        if (g1IsDealer && !g2IsDealer) {
            return -1;
        }
        if (!g1IsDealer && g2IsDealer) {
            return 1;
        }
        return g1.getNickname().compareTo(g2.getNickname());
    }

    private static boolean isDealer(final Gamer gamer) {
        return DEALER_NICKNAME.equals(gamer.getNickname().getDisplayName());
    }
}
