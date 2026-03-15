package controller;

import java.util.List;
import model.BettingResult;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.Player;

public class GameContext {
    private final Dealer dealer;
    private final CardDispenser cardDispenser;
    private final BettingResult bettingResult;
    private List<Player> players;

    public GameContext() {
        dealer = new Dealer();
        Cards cards = Cards.createShuffledDeck();
        this.cardDispenser = new CardDispenser(cards);
        bettingResult = new BettingResult();
    }

    public void setPlayers(List<Player> players) {
        if (this.players != null) {
            throw new IllegalArgumentException("플레이어는 이미 설정되었습니다.");
        }
        this.players = List.copyOf(players);
    }

    public Dealer dealer() {
        return this.dealer;
    }

    public List<Player> players() {
        return this.players;
    }

    public CardDispenser cardDispenser() {
        return this.cardDispenser;
    }

    public BettingResult bettingResult() {
        return this.bettingResult;
    }
}
