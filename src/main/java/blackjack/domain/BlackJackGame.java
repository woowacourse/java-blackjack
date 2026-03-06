package blackjack.domain;

import java.util.HashMap;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void initDraw() {
        for(int i = 0; i < 2; i++) {
            players.recieveCard(deck);
            dealer.recieveCard(deck.draw());
        }
    }

    public HashMap<Player, GameResult> judgeGameResult(){
        BlackJackJudge blackJackJudge = new BlackJackJudge();
        return blackJackJudge.judge( players, dealer);
    }

}
