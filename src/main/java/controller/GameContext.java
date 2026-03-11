package controller;

import java.util.List;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.Player;

public class GameContext {
    private Dealer dealer;
    private List<Player> players;
    private CardDispenser cardDispenser;

    public GameContext() {
        dealer = new Dealer();
        Cards cards = Cards.createShuffledDeck();
        this.cardDispenser = new CardDispenser(cards);
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

    public void setCardDispenser(CardDispenser cardDispenser) {
        this.cardDispenser = cardDispenser;
    }
}
