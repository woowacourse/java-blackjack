package controller;

import java.util.List;
import model.BettingResult;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.Player;

public class GameContext {
    private Dealer dealer;
    private List<Player> players;
    private CardDispenser cardDispenser;
    private BettingResult bettingResult;

    public GameContext() {
        dealer = new Dealer();
        Cards cards = Cards.createShuffledDeck();
        this.cardDispenser = new CardDispenser(cards);
        bettingResult = new BettingResult();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public CardDispenser getCardDispenser() {
        return this.cardDispenser;
    }

    public BettingResult getBettingResult() {
        return this.bettingResult;
    }
}
