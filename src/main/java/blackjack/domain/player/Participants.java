package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int calculateDealerProfit() {
        return -players.stream()
                .mapToInt(player -> player.getBettingAmount()
                        .findProfit(dealer.judgePlayerStatus(player))
                        .getValue())
                .sum();
    }

    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
