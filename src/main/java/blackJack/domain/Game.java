package blackJack.domain;

import blackJack.domain.Card.Cards;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.List;
import java.util.stream.Collectors;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Game {
    private static final int HAND_OUT_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;
    private PlayerScore playerScore = new PlayerScore();
    private DealerScore dealerScore = new DealerScore();

    public Game(List<String> playerNames, Dealer dealer) {
        this.players = new Players(playerNames);
        this.dealer = dealer;
        this.cards = new Cards(CARD_CACHE);
        handOutInitCard();
    }

    private void handOutInitCard() {
        for (int i = 0; i < HAND_OUT_COUNT; i++) {
            dealer.initCard(CARD_CACHE.poll());
            players.recieveCard();
        }
    }

    public boolean checkDealerIsBlackJack() {
        if (dealer.checkBlackJack()) {
            makeBlackjackResult();
            return true;
        }
        return false;
    }

    private void makeBlackjackResult() {
        dealerScore.addResult(Result.WIN, 1);
        for (Player player : players.getPlayers()) {
            distinctBlackjackPlayerResult(player);
        }
    }

    private void distinctBlackjackPlayerResult(Player player) {
        if (player.isBlackJack()) {
            playerScore.addResult(player, Result.DRAW);
        }
        playerScore.addResult(player, Result.LOSE);
    }

    public void makePlayerResult() {
        for (Player player : players.getPlayers()) {
            playerScore.addResult(player, Result.judge(dealer, player));
        }
    }

    public void makeDealerResult(PlayerScore playerScore) {
        List<Result> results = playerScore.getPlayersResult().values().stream().collect(Collectors.toUnmodifiableList());
        for (Result value : Result.values()) {
            dealerScore.addResult(value, getDealerResultCount(Result.reverse(value), results));
        }
    }

    private int getDealerResultCount(Result result, List<Result> results) {
        return (int) results.stream().filter((r) -> r.equals(result)).count();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public DealerScore getDealerScore() {
        return dealerScore;
    }

    public PlayerScore getPlayerScore() {
        return playerScore;
    }
}
