package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackParticipants {
    private final Dealer dealer;
    private final Players players;

    public BlackJackParticipants(final Deck deck, final List<String> nameValues) {
        this.dealer = new Dealer(initCards(deck));
        this.players = new Players(createPlayers(deck, nameValues));
    }

    public BlackJackResults createBlackJackResults() {
        return new BlackJackResults(dealer, players.getPlayers());
    }

    private List<Player> createPlayers(final Deck deck, final List<String> names) {
        return names.stream()
                .map(name -> new Player(initCards(deck), name))
                .collect(Collectors.toList());
    }

    private ParticipantCards initCards(final Deck deck) {
        return new ParticipantCards(List.of(deck.draw(), deck.draw()));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
