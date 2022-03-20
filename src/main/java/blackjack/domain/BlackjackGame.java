package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.PlayerProfit;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int CHANGE_TO_NEGATIVE = -1;

    private final List<Player> players;
    private final Dealer dealer;

    public BlackjackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame create(List<Player> players) {
        return new BlackjackGame(players, Dealer.create());
    }

    public void drawBaseCards() {
        drawCardsByParticipant(dealer);
        for (Player player : players) {
            drawCardsByParticipant(dealer, player);
        }
    }

    private void drawCardsByParticipant(Dealer dealer) {
        if (!dealer.isReady()) {
            dealer.hit(dealer.draw());
            drawCardsByParticipant(dealer);
        }
    }

    private void drawCardsByParticipant(Dealer dealer, Player player) {
        if (!player.isReady()) {
            player.hit(dealer.draw());
            drawCardsByParticipant(dealer, player);
        }
    }

    public void takeMoreCard(Player player) {
        player.hit(dealer.draw());
    }

    public void takeMoreCard() {
        dealer.hit(dealer.draw());
    }

    public List<PlayerProfit> calculatePlayerProfit() {
        return players.stream()
            .map(player -> new PlayerProfit(player.getName(), player.calculateProfit(dealer)))
            .collect(Collectors.toList());
    }

    public int calculateDealerProfit() {
        return calculatePlayerProfit().stream()
            .mapToInt(player -> player.getProfit() * CHANGE_TO_NEGATIVE)
            .sum();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Participant getParticipant() {
        return dealer.getParticipant();
    }

    public List<Participant> getParticipants() {
        return players.stream()
            .map(Player::getParticipant)
            .collect(Collectors.toList());
    }
}
