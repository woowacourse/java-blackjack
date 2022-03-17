package blackjack.domain;

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

    public static BlackJackGame fromPlayerNames(Map<String, String> inputNameAndMoney) {
        Deck deck = new Deck();
        Users users = Users.of(inputNameAndMoney, deck);
        return new BlackJackGame(users, deck);
    }

    public void drawInitialCards(Consumer<Users> consumer) {
        users.drawInitCardsPerUsers(deck);
        consumer.accept(users);
    }

    public boolean isEnd() {
        return users.isDealerBlackjack();
    }

    public void hitOrStayCardsPlayer(Function<User, Supplier<Boolean>> function, Consumer<User> consumer) {
        for (Player player : users.getPlayers()) {
            hitOrStayCards(player, new PlayerHitStrategy(function.apply(player)), consumer);
        }
    }

    public void hitOrStayCardsDealer(Consumer<User> consumer) {
        hitOrStayCards(users.getDealer(), new DealerHitStrategy(users.getDealer().calculateScore()), consumer);
    }

    private void hitOrStayCards(User user, HitStrategy strategy, Consumer<User> consumer) {
        while (user.hitOrStay(deck, strategy)) {
            consumer.accept(user);
        }
    }

    public void settleGame(Consumer<Users> usersConsumer, Consumer<Map<Player, MatchRecord>> matchRecordConsumer) {
        usersConsumer.accept(users);
        matchRecordConsumer.accept(users.createPlayerMatchRecords());
    }

    public void settleGame(Consumer<User> dealerConsumer,
        Consumer<Users> usersConsumer,
        Consumer<Map<Player, MatchRecord>> matchRecordConsumer) {
        dealerConsumer.accept(users.getDealer());
        settleGame(usersConsumer, matchRecordConsumer);
    }
}
