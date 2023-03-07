package blackjack.domain.participant.player;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.ParticipantResultDto;
import blackjack.domain.participant.dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public Players(List<Player> players) {
        this.players = players;
    }


    public Players add(Player player) {
        ArrayList<Player> newPlayers = new ArrayList<>(players);
        newPlayers.add(player);
        return new Players(newPlayers);
    }

    public void hitAdditionalCard(Deck deck, CardDecisionStrategy cardDecisionStrategy,
                                  CardDisplayStrategy cardDisplayStrategy) {
        players.forEach(player -> player.hitAdditionalCardFrom(deck, cardDecisionStrategy, cardDisplayStrategy));
    }

    public void takeCard(Deck deck, int size) {
        players.forEach(player -> player.hit(deck.drawCards(size)));
    }

    public List<ParticipantResultDto> getPlayerResults() {
        return players.stream()
                .map(ParticipantResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void calculateWinning(Dealer dealer) {
        for (Player player : players) {
            player.combat(dealer);
        }
    }

    public List<PlayerWinningDto> getWinningResults() {
        return players.stream()
                .map(PlayerWinningDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<ParticipantCardsDto> getPlayerCards() {
        return players.stream()
                .map(ParticipantCardsDto::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
