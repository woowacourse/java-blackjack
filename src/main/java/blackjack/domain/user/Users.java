package blackjack.domain.user;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    private Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Users of(List<Player> players, Dealer dealer) {
        validateDuplication(players);

        return new Users(players, dealer);
    }

    private static void validateDuplication(List<Player> players) {
        List<String> playerNames = players.stream()
                .map(player -> player.getName())
                .collect(toList());

        HashSet<String> hashSet = new HashSet<>(playerNames);

        if (hashSet.size() < playerNames.size()) {
            throw new IllegalArgumentException("참여자 이름이 중복되면 안됩니다.");
        }
    }

    public void drawCards(Deck deck) {
        for (Player player : players) {
            player.drawCard(deck);
        }
        dealer.drawCard(deck);
    }

    public void drawAdditionalCard(Consumer<User> consumerPlayer, Consumer<User> consumerDealer) {
        List<Player> players = getPlayers();

        for (Player player : players) {
            consumerPlayer.accept(player);
        }

        consumerDealer.accept(getDealer());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
