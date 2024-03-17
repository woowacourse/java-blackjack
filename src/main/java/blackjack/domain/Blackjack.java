package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;
import blackjack.domain.result.*;

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

    public void acceptPlayers(final List<PlayerInfo> playerInfos) {
        final Dealer dealer = createDealer();
        final List<GamePlayer> gamePlayers = createGamePlayers(playerInfos);
        this.players = new Players(dealer, gamePlayers);
    }

    private Dealer createDealer() {
        final Dealer dealer = Dealer.createDefaultNameDealer();
        participantDrawCard(dealer);
        participantDrawCard(dealer);
        return dealer;
    }

    private List<GamePlayer> createGamePlayers(final List<PlayerInfo> playerInfos) {
        return playerInfos.stream()
                          .map(this::createGamePlayer)
                          .toList();
    }

    private GamePlayer createGamePlayer(final PlayerInfo playerInfo) {
        final GamePlayer gamePlayer = new GamePlayer(playerInfo);
        participantDrawCard(gamePlayer);
        participantDrawCard(gamePlayer);
        return gamePlayer;
    }

    public void participantHitCard(final Participant participant) {
        participantDrawCard(participant);
    }

    private void participantDrawCard(final Participant participant) {
        participant.drawCard(deck.draw());
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
        return new GamePlayerResult(gamePlayer.getName(), PrizeChecker.check(dealer, gamePlayer));
    }

    public Dealer getDealer() {
        return players.dealer();
    }

    public List<GamePlayer> getGamePlayers() {
        return Collections.unmodifiableList(players.gamePlayers());
    }
}
