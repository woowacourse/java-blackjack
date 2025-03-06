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

    private List<PlayerResponse> openParticipantsCards(List<Participant> participants) {
        return participants.stream()
                .map(participant -> new PlayerResponse(participant.getName(), participant.openInitialCards()))
                .toList();
    }

    public PlayerResponse addCardToCurrentParticipant(String name) {
        Player participant = players.getPlayerByName(name);
        participant.addCard(deck);
        return new PlayerResponse(
                participant.getName(),
                participant.getCards().getCards()
        );
    }

    public PlayerResponse getPlayerByName(String name) {
        Player player = players.getPlayerByName(name);
        return new PlayerResponse(
                player.getName(),
                player.getCards().getCards()
        );
    }

    public List<String> getParticipantNames() {
        return getParticipants().stream()
                .map(Player::getName)
                .toList();
    }

    private Player getDealer() {
        return players.getDealer();
    }

    private List<Participant> getParticipants() {
        return players.getParticipants()
                .stream()
                .map(player -> (Participant) player)
                .toList();
    }
}
