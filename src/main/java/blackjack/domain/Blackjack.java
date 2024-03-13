package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.domain.result.*;

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

    private Cards drawTwo() {
        return new Cards(List.of(deck.draw(), deck.draw()));
    }

    public void participantHitCard(final Participant participant) {
        participant.drawCard(draw());
    }

    private Card draw() {
        return deck.draw();
    }

    public Result checkResult() {
        final List<GamePlayerResult> gamePlayerResults = checkPlayersResult();
        final DealerResult dealerResult = DealerResult.of(players.dealer()
                                                                 .getName(), gamePlayerResults);
        return new Result(gamePlayerResults, dealerResult);
    }

    private List<GamePlayerResult> checkPlayersResult() {
        return players.gamePlayers()
                      .stream()
                      .map(gamePlayer -> checkPlayerResult(players.dealer(), gamePlayer))
                      .toList();
    }

    private GamePlayerResult checkPlayerResult(final Dealer dealer, final GamePlayer gamePlayer) {
        return new GamePlayerResult(gamePlayer.getName(), ResultChecker.calculate(dealer, gamePlayer));
    }

    public Dealer getDealer() {
        return players.dealer();
    }

    public List<GamePlayer> getGamePlayers() {
        return Collections.unmodifiableList(players.gamePlayers());
    }
}
