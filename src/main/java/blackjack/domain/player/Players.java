package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.exception.DealerNotFoundException;
import blackjack.domain.player.exception.DuplicatedPlayerNameException;
import blackjack.domain.player.exception.InvalidChallengerNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int CHALLENGER_MAX_COUNT = 10;
    private static final int CHALLENGER_MIN_COUNT = 1;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        validate(names);
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        names.stream()
                .map(Challenger::new)
                .forEach(players::add);
        return new Players(players);
    }

    private static void validate(final List<String> names) {
        validateChallengerCount(names);
        validateDuplicatedNames(names);
    }

    private static void validateChallengerCount(final List<String> names) {
        if (names.size() > CHALLENGER_MAX_COUNT || names.size() < CHALLENGER_MIN_COUNT) {
            throw new InvalidChallengerNumberException();
        }
    }

    private static void validateDuplicatedNames(final List<String> names) {
        long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new DuplicatedPlayerNameException();
        }
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
