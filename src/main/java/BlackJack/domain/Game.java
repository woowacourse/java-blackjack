package BlackJack.domain;

import BlackJack.domain.Card.Cards;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import BlackJack.domain.User.Players;

import java.util.List;
import java.util.stream.Collectors;

import static BlackJack.domain.Card.CardFactory.CARD_CACHE;

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
    }

    public void handOutInitCard(){
        for(int i = 0; i < HAND_OUT_COUNT; i++){
            dealer.initCard(CARD_CACHE.poll());
            players.recieveCard();
        }
    }

    public void checkPlayerAndDealerIsBlackJack(){
        dealer.checkBlackJack();
        players.checkPlayersBlackJack();
    }

    private Players playGame() {
        players.addCard();
        return players;
    }

    public Dealer checkDealerAddCard(){
        dealer.addCard();
        return dealer;
    public void makePlayerResult() {
        for (Player player : players.getPlayers()) {
            playerScore.addResult(player,Result.judge(dealer,player));
        }
    }

    public void makeDealerResult(PlayerScore playerScore){
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
