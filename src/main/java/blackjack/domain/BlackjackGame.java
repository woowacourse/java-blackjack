package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.PlayerNames;
import blackjack.domain.player.Players;
import blackjack.domain.rule.state.InitState;
import blackjack.dto.StartCardsDto;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private BlackjackGame(final Players players, final Dealer dealer, final Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame from(final PlayerNames playerNames) {
        final Deck deck = Deck.create();
        final Dealer dealer = new Dealer();
        final Players players = Players.from(playerNames);

        return new BlackjackGame(players, dealer, deck);
    }

    public StartCardsDto giveInitCards() {
        for (int i = 0; i < InitState.START_CARD_COUNT; i++) {
            dealer.draw(deck.pick());
            playersDraw();
        }

        return StartCardsDto.of(players.getPlayersHands(), dealer.getOpenedHands());
    }

    private void playersDraw() {
        for (final Player player : players.getPlayers()) {
            player.draw(deck.pick());
        }
    }

    public void playGame(
            final Predicate<PlayerName> userWantToHit,
            final BiConsumer<PlayerName, Hands> playerCallAfter,
            final IntConsumer dealerCallAfter
    ) {
        runPlayersTurn(userWantToHit, playerCallAfter);
        runDealerTurn(dealerCallAfter);
    }

    private void runPlayersTurn(
            final Predicate<PlayerName> userWantToHit,
            final BiConsumer<PlayerName, Hands> playerCallAfter
    ) {
        for (final Player player : players.getPlayers()) {
            runPlayerTurn(userWantToHit, playerCallAfter, player);
        }
    }

    private void runPlayerTurn(
            final Predicate<PlayerName> userWantToHit,
            final BiConsumer<PlayerName, Hands> playerCallAfter,
            final Player player
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
            final Player player,
            final Predicate<PlayerName> userWantToHit
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

    public Map<PlayerName, Hands> getPlayerHands() {
        return players.getPlayersHands();
    }

    public Hands getDealerHands() {
        return dealer.getHands();
    }

    public Map<PlayerName, BetLeverage> getPlayersBetLeverage() {
        return players.getPlayers().stream()
                .collect(toMap(Player::getPlayerName,
                        player -> BetLeverage.of(player.getState(), dealer.getState())));
    }
}
