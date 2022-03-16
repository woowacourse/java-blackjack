package blackjack.domain.entry;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
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

    public Map<Player, Double> getGameResults() {
        return players.stream()
            .collect(Collectors.toMap(player -> player, player -> player.getBettingResult(dealer)));
    }
}
