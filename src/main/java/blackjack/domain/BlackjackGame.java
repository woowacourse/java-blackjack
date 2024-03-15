package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.player.UserName;
import blackjack.domain.rule.state.InitState;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
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

    public static BlackjackGame of(final PlayerNames playerNames, final User dealer) {
        final Deck deck = Deck.create();
        final Players players = Players.from(playerNames);

        return new BlackjackGame(players, dealer, deck);
    }

    public void giveStartCards() {
        for (int i = 0; i < InitState.START_CARD_COUNT; i++) {
            dealer.draw(deck.pick());
            playersDraw();
        }
    }

    public Map<UserName, Hands> getPlayersOpenedHands() {
        return players.getPlayersOpendHands();
    }

    public Hands getDealerOpenedHands() {
        return dealer.getOpenedHands();
    }

    private void playersDraw() {
        for (final User player : players.getPlayers()) {
            player.draw(deck.pick());
        }
    }

    public void playGame(
            final Predicate<UserName> userWantToHit,
            final BiConsumer<UserName, Hands> playerCallAfter,
            final IntConsumer dealerCallAfter
    ) {
        runPlayersTurn(userWantToHit, playerCallAfter);
        runDealerTurn(dealerCallAfter);
    }

    private void runPlayersTurn(
            final Predicate<UserName> userWantToHit,
            final BiConsumer<UserName, Hands> playerCallAfter
    ) {
        for (final User player : players.getPlayers()) {
            runPlayerTurn(player, userWantToHit, playerCallAfter);
        }
    }

    private void runPlayerTurn(
            final User player,
            final Predicate<UserName> userWantToHit,
            final BiConsumer<UserName, Hands> playerCallAfter
    ) {
        if (dealer.isFinished()) {
            player.stand();
            return;
        }
        while (player.canHit()) {
            runHitOrStandByUser(player, userWantToHit);
            playerCallAfter.accept(player.getPlayerName(), player.getHands());
        }
    }

    private void runHitOrStandByUser(
            final User player,
            final Predicate<UserName> userWantToHit
    ) {
        if (userWantToHit.test(player.getPlayerName())) {
            final Card card = deck.pick();
            player.draw(card);
            return;
        }

        if (player.isNotFinished()) {
            player.stand();
        }
    }

    private void runDealerTurn(final IntConsumer dealerHitConsumer) {
        int count = 0;
        while (players.hasStandPlayer() && dealer.canHit()) {
            final Card card = deck.pick();
            dealer.draw(card);
            count++;
        }

        if (dealer.isNotFinished()) {
            dealer.stand();
        }
        dealerHitConsumer.accept(count);
    }

    public Map<UserName, Hands> getPlayerHands() {
        return players.getPlayersOpendHands();
    }

    public Hands getDealerHands() {
        return dealer.getHands();
    }

    public Map<UserName, BetLeverage> getPlayersBetLeverage() {
        return players.getPlayers().stream()
                .collect(toMap(User::getPlayerName,
                        player -> BetLeverage.of(player.getState(), dealer.getState())));
    }
}
