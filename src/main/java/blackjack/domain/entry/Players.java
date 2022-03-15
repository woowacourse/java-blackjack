package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
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

    public boolean isHitDealer() {
        return dealer.canHit();
    }

    public void hitDealer(Card card) {
        dealer.addCard(card);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public Map<PlayerOutcome, List<Player>> getGameResults() {
        return players.stream()
                .collect(Collectors.groupingBy(player -> player.match(dealer)));
    }
}
