package blackjack.domain.participant.player;

import blackjack.controller.AddCardOrNot;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.ParticipantResultDto;
import blackjack.domain.participant.dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players = new ArrayList<>();


    public void add(Player player) {
        players.add(player);
    }

    public void hitAdditionalCard(Deck deck, Function<Name, AddCardOrNot> decideAddCardOrNot,
                                  Consumer<Player> showPlayerCards) {
        players.forEach(player -> player.hitAdditionalCardFrom(deck, decideAddCardOrNot, showPlayerCards));
    }

    public void takeCard(Deck deck, int size) {
        players.forEach(player -> player.hit(deck.drawCards(size)));
    }

    public int count() {
        return players.size();
    }

    public boolean isBust(int playerIndex) {
        return players.get(playerIndex).isBust();
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
