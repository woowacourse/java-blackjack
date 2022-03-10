package blackjack.domain.entry;

import blackjack.domain.Outcome;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final Dealer dealer;
    private final List<Player> players;

    public Players(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean shouldDealerHit(Deck deck) {
        if (dealer.shouldHaveMoreCard()) {
            dealer.putCard(deck.draw());
            return true;
        }
        return false;
    }

    public Map<Outcome, List<Player>> getGameResult() {
        return players.stream()
                .collect(Collectors.groupingBy(player -> player.isWin(dealer.countCards())));
    }

    public List<Participant> getParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }
}
