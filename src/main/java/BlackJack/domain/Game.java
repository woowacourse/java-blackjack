package BlackJack.domain;

import BlackJack.domain.Card.Cards;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Players;
import BlackJack.domain.User.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static BlackJack.domain.Card.CardFactory.CARD_CACHE;

public class Game {
    private static final int HAND_OUT_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    public Game(List<String> playerNames, Dealer dealer) {
        this.players = new Players(playerNames);
        this.dealer = dealer;
        this.cards = new Cards(CARD_CACHE);
    }

    public void handOutInitCard(){
        for(int i = 0; i < HAND_OUT_COUNT; i++){
            dealer.addCard(CARD_CACHE.poll());
            players.recieveCard();
        }
    }

    public void checkPlayerAndDealerIsBlackJack(){
        dealer.checkBlackJack();
        players.checkPlayersBlackJack();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
