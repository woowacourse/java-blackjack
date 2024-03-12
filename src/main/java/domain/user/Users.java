package domain.user;

import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.Deck;
import domain.card.Card;
import domain.game.GameResult;
import java.util.List;

public class Users {
    private final Players players;
    private final Dealer dealer;

    public Users(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void putStartCards(Deck deck) {
        players.putStartCards(deck);
        dealer.putStartCards(deck);
    }

    public boolean isDealerCardAddable() {
        return dealer.isAddable();
    }

    public void addDealerCard(Card card) {
        dealer.addCard(card);
    }

    public GameResult generatePlayerResult(Player player) {
        if (player.busted()) {
            return LOSE;
        }
        if (dealer.busted()) {
            return WIN;
        }
        return GameResult.compare(player.sumHand(), dealer.sumHand());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
