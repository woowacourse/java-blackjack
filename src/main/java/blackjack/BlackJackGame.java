package blackjack;

import blackjack.domain.Answer;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackReferee;

import java.util.List;
import java.util.function.UnaryOperator;

public class BlackJackGame {
    private final Deck deck;

    private final Gamers gamers;

    public BlackJackGame(List<String> names) {
        this.deck = new Deck(Card.getCards());
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

    public boolean isDrawPossible(String name, UnaryOperator<String> operator) {
        return !gamers.isBurst(name) && Answer.from(operator.apply(name)).isYes();
    }

    public void distributeCardToPlayer(String name) {
        gamers.distributeCardToPlayer(name, deck);
    }

    public Player findPlayerByName(String name) {
        return gamers.findPlayerByName(name);
    }

    public int getDealerCardSize() {
        return gamers.getDealerCardSize();
    }

    public BlackJackReferee createResult() {
        return new BlackJackReferee(gamers.getPlayers(), gamers.getDealer());
    }
}
