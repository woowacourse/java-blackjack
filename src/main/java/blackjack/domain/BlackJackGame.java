package blackjack.domain;

import blackjack.domain.Answer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackReferee;

import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final Gamers gamers;

    public BlackJackGame(List<String> names) {
        this.deck = new Deck();
        gamers = new Gamers(names);
        gamers.distributeFirstCards(deck);
    }

    public void distributeAdditionalToDealer() {
        gamers.distributeAdditionalToDealer(deck);
    }

    public Dealer getDealer() {
        return gamers.getDealer();
    }

    public List<Player> getPlayers() {
        return gamers.getPlayers();
    }

    public boolean isDrawPossible(Player player, String value) {
        return !gamers.canDrawToPlayer(player) && Answer.from(value).isYes();
    }

    public void distributeCardToPlayer(Player player) {
        player.addCard(deck.draw());
    }

    public int getDealerCardSize() {
        return gamers.getDealerCardSize();
    }

    public BlackJackReferee createResult() {
        return new BlackJackReferee(gamers.getPlayers(), gamers.getDealer());
    }
}
