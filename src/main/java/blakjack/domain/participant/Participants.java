package blakjack.domain.participant;

import blakjack.domain.card.Card;
import blakjack.domain.card.CardDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Participants {
    private static final String INVALID_NAME_MESSAGE = "등록되지 않은 이름입니다.";

    private final Participant dealer = new Dealer();
    private final List<Participant> players;

    public Participants(final List<Participant> players) {
        this.players = players;
    }

    public void initCards(final CardDeck cardDeck) {
        dealer.initCards(cardDeck);
        for (final Participant player : players) {
            player.initCards(cardDeck);
        }
    }

    public List<Participant> getPlayers() {
        return new ArrayList<>(players);
    }

    public Participant getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void hitPlayer(final String name, final Card card) {
        findPlayerByName(name).hit(card);
    }

    public Participant findPlayerByName(final String name) {
        return players.stream()
                .filter(participant -> participant.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NAME_MESSAGE));
    }

    public void stay(final String name) {
        findPlayerByName(name).stay();
    }

    public void hitDealer(final Card card) {
        dealer.hit(card);
    }
}
