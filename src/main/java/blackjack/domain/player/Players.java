package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.exception.DuplicatedPlayerNameException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        validateDuplicatedNames(names);
        List<Player> players = new ArrayList<>();
        players.add(new Dealer());
        names.stream()
                .map(Challenger::new)
                .forEach(players::add);
        return new Players(players);
    }

    private static void validateDuplicatedNames(List<String> names) {
        long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new DuplicatedPlayerNameException();
        }
    }

    public void pickStartCards(CardDeck cardDeck) {
        for (Player player : players) {
            Card firstCard = cardDeck.pick();
            Card secondCard = cardDeck.pick();
            player.pickStartCards(firstCard, secondCard);
        }
    }

    public List<Challenger> getChallengers() {
        return players.stream()
                .filter(player -> !player.isDealer())
                .map(player -> (Challenger) player)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return players.stream()
                .filter(player -> player.isDealer())
                .map(player -> (Dealer) player)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }
}
