package blackjack.domain;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;
import blackjack.domain.strategy.hit.DealerHitStrategy;
import blackjack.domain.strategy.hit.HitStrategy;
import blackjack.domain.strategy.hit.PlayerHitStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

public final class BlackJackGame {

    private final Users users;
    private final Deck deck;

    private BlackJackGame(Users users, Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJackGame fromPlayerNames(Map<String, String> inputNameAndMoney) {
        Deck deck = new Deck();
        Users users = Users.of(inputNameAndMoney);
        return new BlackJackGame(users, deck);
    }

    public void drawInitialCards(Consumer<Users> initUsersConsumer) {
        users.drawInitialCardsPerUser(deck);
        initUsersConsumer.accept(users);
    }

    public boolean isEnd() {
        return users.isDealerBlackjack();
    }

    public void hitOrStayCardsPlayer(Function<User, Supplier<Boolean>> playerHitSupplierFunction,
        Consumer<User> userConsumer) {

        for (Player player : users.getPlayers()) {
            hitOrStayCards(player, new PlayerHitStrategy(playerHitSupplierFunction.apply(player)), userConsumer);
        }
    }

    public void hitOrStayCardsDealer(Consumer<User> userConsumer) {
        hitOrStayCards(users.getDealer(), new DealerHitStrategy(() -> users.getDealer().getScore()), userConsumer);
    }

    private void hitOrStayCards(User user, HitStrategy strategy, Consumer<User> userConsumer) {
        while (user.hitOrStay(deck, strategy)) {
            userConsumer.accept(user);
        }
    }

    public void settleGame(Consumer<Users> usersConsumer, BiConsumer<Dealer, Map<Player, Money>> userMoneyConsumer) {
        usersConsumer.accept(users);
        userMoneyConsumer.accept(users.getDealer(), users.createPlayerProfit());
    }

    public void settleGameEarly(Consumer<User> dealerConsumer,
        Consumer<Users> usersConsumer,
        BiConsumer<Dealer, Map<Player, Money>> playerMoneyConsumer) {

        users.stayAllPlayers();
        dealerConsumer.accept(users.getDealer());
        settleGame(usersConsumer, playerMoneyConsumer);
    }
}
