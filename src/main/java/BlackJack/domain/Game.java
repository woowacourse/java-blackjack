package BlackJack.domain;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import BlackJack.domain.User.Players;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

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




}
