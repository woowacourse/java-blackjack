package blackjack.domain;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.User;
import blackjack.domain.player.UserName;
import blackjack.domain.player.Users;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class BlackjackGame {

    private final Users users;
    private final Deck deck;

    private BlackjackGame(final Users users, final Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackjackGame of(final PlayerNames playerNames, final Predicate<UserName> playerWantToHit) {
        final Users users = Users.of(playerNames, playerWantToHit);
        final Deck deck = Deck.create();

        return new BlackjackGame(users, deck);
    }

    public void giveStartCards() {
        users.drawStartCard(deck);
    }

    public void playGame(final BiConsumer<UserName, Hands> userCall) {
        for (final User user : users.getUsers()) {
            runUserTurn(user, userCall);
        }
    }

    private void runUserTurn(final User user, final BiConsumer<UserName, Hands> playerCallAfter) {
        if (users.isAllPlayerBust()) {
            return;
        }

        while (user.isNotFinished()) {
            user.playTurn(deck);
            playerCallAfter.accept(user.getUserName(), user.getHands());
        }
    }

    public Map<UserName, BetLeverage> getPlayersBetLeverage() {
        return users.getPlayersBetLeverage();
    }

    public Map<UserName, Hands> getPlayersHands() {
        return users.getPlayersOpenedHands();
    }

    public Hands getDealerOpenedHands() {
        return users.getDealerOpenedHands();
    }

    public Hands getDealerHands() {
        return users.getDealerHands();
    }
}
