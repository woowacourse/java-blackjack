package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blackjack {
    private final Deck deck;
    private Players players;

    public Blackjack(final Deck deck) {
        this.deck = deck;
    }

    public Blackjack() {
        deck = Deck.createPack();
    }

    public void acceptPlayers(final Names names) {
        final Dealer dealer = Dealer.createDefaultDealer(drawTwo());
        final List<GamePlayer> gamePlayers = names.stream()
                                                  .map(name -> new GamePlayer(name, drawTwo()))
                                                  .toList();
        this.players = new Players(dealer, gamePlayers);
    }

    public Result checkPlayersResult() {
        final List<GamePlayerResult> gamePlayerResults = new ArrayList<>();

        for (final GamePlayer gamePlayer : players.gamePlayers()) {
            gamePlayerResults.add(
                    new GamePlayerResult(gamePlayer.getName(), players.dealer()
                                                                      .checkPlayer(gamePlayer)));
        }

        return new Result(gamePlayerResults, DealerResult.of(players.dealer()
                                                                    .getName(), gamePlayerResults));
    }

    private Cards drawTwo() {
        return new Cards(List.of(deck.draw(), deck.draw()));
    }

    public void participantHitCard(final Participant participant) {
        participant.drawCard(draw());
    }

    private Card draw() {
        return deck.draw();
    }

    public Dealer getDealer() {
        return players.dealer();
    }

    public List<GamePlayer> getGamePlayers() {
        return Collections.unmodifiableList(players.gamePlayers());
    }
}
