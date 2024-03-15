package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.*;

public class BlackJackGame {

    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.dealer = dealer;
        this.players = players.getPlayers();
    }

    public void initHand() {
        dealer.initHand(dealer.dealCard(2));

        for (Player player : players) {
            player.initHand(dealer.dealCard(2));
        }
    }

    public void hitParticipant(Participant participant) {
        participant.hit(dealer.dealCard());
    }

    public void hitDealer() {
        hitParticipant(dealer);
    }

    public boolean isDealerHittable() {
        return dealer.isHittable();
    }

    public Map<Player, PlayerGameResult> getPlayerGameResult() {
        Map<Player, PlayerGameResult> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, PlayerGameResult.of(dealer, player));
        }
        return results;
    }

    public List<Participant> getEveryParticipants() {
        List<Participant> participants = new LinkedList<>(players);
        participants.add(0, dealer);
        return Collections.unmodifiableList(participants);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
