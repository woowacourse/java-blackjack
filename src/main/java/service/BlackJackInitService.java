package service;

import domain.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public class BlackJackInitService {

    public Deck createDeck() {
        return Deck.createShuffledDeck();
    }

    public Dealer createDealer(Deck deck) {
        Dealer dealer = new Dealer();
        dealer.draw(deck.drawCard());
        dealer.draw(deck.drawCard());
        return dealer;
    }

    public List<Player> createPlayers(List<String> names, Deck deck) {
        return names.stream()
                .map(name -> {
                    Player player = new Player(name);
                    player.draw(deck.drawCard());
                    player.draw(deck.drawCard());
                    return player;
                })
                .toList();
    }
}
