package domain.gamer;

import domain.deck.Card;
import domain.deck.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    final List<Gamer> participants;

    private Participants(final List<Gamer> participants) {
        this.participants = participants;
    }

    public static Participants of(final List<Player> players, final Gamer dealer) {
        final List<Gamer> values = new ArrayList<>(players);
        values.add(dealer);
        return new Participants(values);
    }

    public void dealInitialCards(final Deck deck) {
        for (final Gamer gamer : participants) {
            final Card firstCard = deck.drawCard();
            final Card secondCard = deck.drawCard();
            gamer.receiveInitialCards(List.of(firstCard, secondCard));
        }
    }

    public Map<Nickname, List<Card>> getCardsAtStartWithNickname() {
        return participants.stream()
                .collect(Collectors.toMap(Gamer::getNickname, Gamer::getCards));
    }

    public List<Gamer> getParticipants() {
        return participants;
    }
}
