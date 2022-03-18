package blackJack.domain;

import blackJack.domain.Card.Cards;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Game {
    private static final int HAND_OUT_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;
    private PlayerScore playerScore = new PlayerScore();
    private DealerScore dealerScore = new DealerScore();

    public Game(List<String> playerNames, Dealer dealer, Map<String, Integer> bettingMoneys) {
        this.players = new Players(playerNames,bettingMoneys);
        this.dealer = dealer;
        this.cards = new Cards(CARD_CACHE);
        handOutInitCard();
    }

    private void handOutInitCard() {
        for (int i = 0; i < HAND_OUT_COUNT; i++) {
            dealer.dealCard(CARD_CACHE.poll());
            players.dealCardToPlayers();
        }
    }

    public void makeFinalResult(){
        playerScore.makePlayerResult(dealer,players);
        dealerScore.makeDealerResult(playerScore);
    }

    public boolean checkDealerIsBlackJack() {
        if (dealer.isBlackJack()) {
            playerScore.makeBlackjackResult(players);
            dealerScore.makeDealerResult(playerScore);
            return true;
        }
        return false;
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
