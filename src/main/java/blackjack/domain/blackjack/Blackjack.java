package blackjack.domain.blackjack;

import blackjack.domain.player.*;
import blackjack.domain.player.info.PlayerInfo;
import blackjack.domain.result.*;
import blackjack.domain.result.prize.PrizeChecker;

import java.util.Collections;
import java.util.List;

public class Blackjack {
    private final Deck deck;
    private Players players;

    public Blackjack(final Deck deck) {
        this.deck = deck;
    }

    public Blackjack() {
        this.deck = Deck.createPack();
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
        participant.drawCard(this.deck.draw());
    }

    public Result checkResult() {
        final List<GamePlayerResult> gamePlayerResults = checkPlayersResult();
        final DealerResult dealerResult = DealerResult.of(this.players.dealer()
                                                                      .getName(), gamePlayerResults);
        return new Result(gamePlayerResults, dealerResult);
    }

    private List<GamePlayerResult> checkPlayersResult() {
        return this.players.gamePlayers()
                           .stream()
                           .map(gamePlayer -> checkPlayerResult(gamePlayer, this.players.dealer()))
                           .toList();
    }

    private GamePlayerResult checkPlayerResult(final GamePlayer gamePlayer, final Dealer dealer) {
        return new GamePlayerResult(gamePlayer.getName(), PrizeChecker.check(gamePlayer, dealer));
    }

    public Dealer getDealer() {
        return this.players.dealer();
    }

    public List<GamePlayer> getGamePlayers() {
        return Collections.unmodifiableList(this.players.gamePlayers());
    }
}
