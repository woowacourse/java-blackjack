package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.exception.DealerNotFoundException;
import blackjack.dto.ChallengerNameAndMoneyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<ChallengerNameAndMoneyDto> challengerNameAndMoneyDtos) {
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        challengerNameAndMoneyDtos.stream()
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

    public List<Challenger> getChallengers() {
        List<Challenger> challengers = players.stream()
                .filter(Player::isChallenger)
                .map(player -> (Challenger) player)
                .collect(Collectors.toUnmodifiableList());
        return new ArrayList<>(challengers);
    }

    public Dealer getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .map(player -> (Dealer) player)
                .findFirst()
                .orElseThrow(DealerNotFoundException::new);
    }
}
