package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.hit.DealerHitStrategy;
import blackjack.domain.strategy.hit.HitStrategy;
import blackjack.domain.strategy.hit.PlayerHitStrategy;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

public class BlackJackGame {

    private final Users users;
    private final Deck deck;

    private BlackJackGame(Users users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJackGame fromPlayerNames(List<String> inputPlayerNames) {
        Users users = Users.from(inputPlayerNames);
        Deck deck = new Deck();
        return new BlackJackGame(users, deck);
    }

    public void drawInitialCards(Consumer<Users> consumer) {
        users.drawInitCards(deck);
        consumer.accept(users);
    }

    public void hitOrStayCardsPlayer(Function<User, Supplier<String>> function, Consumer<User> consumer) {
        for (Player player : users.getPlayers()) {
            hitOrStayCards(player, new PlayerHitStrategy(function.apply(player)), consumer);
        }
    }

    public void hitOrStayCardsDealer(Consumer<User> consumer) {
        hitOrStayCards(users.getDealer(), new DealerHitStrategy(users.getDealer().getScore()), consumer);
    }

    private void hitOrStayCards(User user, HitStrategy strategy, Consumer<User> consumer) {
        boolean isHit = user.hitOrStay(deck, strategy);
        consumer.accept(user);
        if (isHit) {
            hitOrStayCards(user, strategy, consumer);
        }
    }

    public void settleGame(Consumer<Users> usersConsumer, Consumer<Map<Player, MatchRecord>> matchRecordConsumer) {
        usersConsumer.accept(users);
        matchRecordConsumer.accept(users.createPlayerMatchRecords());
    }
}
