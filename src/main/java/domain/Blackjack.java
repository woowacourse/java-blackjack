package domain;

import domain.dto.OpenCardsResponse;
import domain.dto.PlayerResponse;
import java.util.List;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public OpenCardsResponse openPlayersCards() {
        return new OpenCardsResponse(
                getPlayerResponse(getDealer()),
                getPlayerResponses(getParticipants())
        );
    }

    private PlayerResponse getPlayerResponse(Player player) {
        return new PlayerResponse(player.getName(), player.getCards());
    }

    private List<PlayerResponse> getPlayerResponses(List<Player> players) {
        return players.stream()
                .map(participant -> new PlayerResponse(participant.getName(), participant.getCards()))
                .toList();
    }

    private Player getDealer() {
        return players.getDealer();
    }

    private List<Player> getParticipants() {
        return players.getParticipants();
    }
}
