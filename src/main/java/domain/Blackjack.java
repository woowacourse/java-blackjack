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

    public OpenCardsResponse openInitialCards() {
        return new OpenCardsResponse(
                openDealerCards(getDealer()),
                openParticipantsCards(getParticipants())
        );
    }

    private PlayerResponse openDealerCards(Player player) {
        return new PlayerResponse(player.getName(), player.openInitialCards());
    }

    private List<PlayerResponse> openParticipantsCards(List<Player> players) {
        return players.stream()
                .map(participant -> new PlayerResponse(participant.getName(), participant.openInitialCards()))
                .toList();
    }

    private Player getDealer() {
        return players.getDealer();
    }

    private List<Player> getParticipants() {
        return players.getParticipants();
    }
}
