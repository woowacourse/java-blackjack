package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.exception.DealerNotFoundException;
import blackjack.dto.ChallengerMoneyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        names.stream()
                .map(Challenger::new)
                .forEach(players::add);
        return new Players(players);
    }

    public static Players of(final List<ChallengerMoneyDto> challengerMoneyDtos) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        challengerMoneyDtos.stream()
                .map(Challenger::new)
                .forEach(players::add);
        return new Players(players);
    }

    public void pickStartCards(final CardDeck cardDeck) {
        for (Player player : players) {
            Card firstCard = cardDeck.pick();
            Card secondCard = cardDeck.pick();
            player.pickStartCards(firstCard, secondCard);
        }
    }

    public List<Player> getChallengers() {
        List<Player> challengers = players.stream()
                .filter(Player::isChallenger)
                .collect(Collectors.toUnmodifiableList());
        return new ArrayList<>(challengers);
    }

    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(DealerNotFoundException::new);
    }
}
