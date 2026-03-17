package controller;

import java.util.ArrayList;
import java.util.List;
import model.BettingResult;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.Participant;
import model.Player;

public class GameContext {
    private final Dealer dealer;
    private final CardDispenser cardDispenser;
    private final BettingResult bettingResult;
    private final List<Player> players;

    public GameContext() {
        dealer = new Dealer();
        Cards cards = Cards.createShuffledDeck();
        this.cardDispenser = new CardDispenser(cards);
        bettingResult = new BettingResult();
        players = new ArrayList<>();
    }

    public void initializePlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public Dealer dealer() {
        return this.dealer;
    }

    public List<Player> players() {
        return this.players;
    }

    public void dispenseOneCard(Participant player) {
        this.cardDispenser.dispenseOneCard(player);
    }

    public void dispenseStartingCards(Participant player) {
        this.cardDispenser.dispenseStartingCards(player);
    }

    public void calculateBettingMoney() {
        this.bettingResult.calculateBettingMoney(this.dealer, this.players);
    }

    public List<Participant> participantsBettingResults() {
        return this.bettingResult.participantsBettingResults();
    }

    public BettingResult bettingResult() {
        return this.bettingResult;
    }
}
