package domain;

import static domain.GameResult.comparePlayerWithDealer;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {

    private final Users users;
    private final Deck deck;

    private BlackJack(final Users users, final Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJack of(final Users users, final CardIndexGenerator cardIndexGenerator) {
        Deck deck = Deck.from(cardIndexGenerator);
        initCards(users, deck);
        return new BlackJack(users, deck);
    }

    private static void initCards(Users users, Deck deck) {
        for (Player player : users.getPlayers()) {
            hitTwoCards(player, deck);
        }
        hitTwoCards(users.getDealer(), deck);
    }

    private static void hitTwoCards(User user, Deck deck) {
        user.hit(deck.pickCard());
        user.hit(deck.pickCard());
    }

    public Map<Player, GameResult> calculateGameResults() {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        int dealerScore = dealer.getScore();
        return players.stream()
            .collect(
                Collectors.toMap(player -> player, player -> comparePlayerWithDealer(player.getScore(), dealerScore)));
    }
}
