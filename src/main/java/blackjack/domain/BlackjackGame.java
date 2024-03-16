package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.player.UserName;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BlackjackGame {

    private final Players players;
    private final User dealer;
    private final Deck deck;

    private BlackjackGame(final Players players, final User dealer, final Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame of(final PlayerNames playerNames, final Predicate<UserName> playerWantToHit) {
        final Players players = Players.of(playerNames, playerWantToHit);
        final Dealer dealer = new Dealer();
        final Deck deck = Deck.create();

        return new BlackjackGame(players, dealer, deck);
    }

    public void giveStartCards() {
        final List<User> users = players.getPlayers();
        users.add(dealer);

        for (final User user : users) {
            userDrawStartCard(user);
        }
    }

    private void userDrawStartCard(final User user) {
        while (user.isInitState()) {
            user.playTurn(deck);
        }
    }

    public void playGame(final BiConsumer<UserName, Hands> userCall) {
        final List<User> users = players.getPlayers();
        users.add(dealer);

        for (final User user : users) {
            runUserTurn(user, userCall);
        }
    }

    private void runUserTurn(final User user, final BiConsumer<UserName, Hands> playerCallAfter) {
        while (user.isNotFinished()) {
            user.playTurn(deck);
            playerCallAfter.accept(user.getUserName(), user.getHands());
        }
    }

    public Map<UserName, Hands> getPlayersHands() {
        return players.getPlayersOpendHands();
    }

    public Hands getDealerOpenedHands() {
        return dealer.getOpenedHands();
    }

    public Hands getDealerHands() {
        return dealer.getHands();
    }

    public Map<UserName, BetLeverage> getPlayersBetLeverage() {
        return players.getPlayers().stream()
                .collect(toMap(User::getUserName, player -> BetLeverage.of(player.getState(), dealer.getState())));
    }
}
