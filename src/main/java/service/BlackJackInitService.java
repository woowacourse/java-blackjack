package service;

import domain.Deck;
import domain.card.Card;
import domain.card.Name;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public class BlackJackInitService {

    public Deck createDeck() {
        return Deck.createShuffledDeck();
    }

    // todo 딜러도 카드리스트 주입!
    public Dealer createDealer(Deck deck) {
        Dealer dealer = new Dealer();
        dealer.draw(deck.drawCard());
        dealer.draw(deck.drawCard());
        return dealer;
    }

    public List<Player> createPlayers(List<String> names, Deck deck) {
        return names.stream()
                .map(name -> {
                    List<Card> cards = List.of(deck.drawCard(), deck.drawCard());
                    Name nameObject = new Name(name);
                    return Player.of(nameObject, cards);
                })
                .toList();
    }
}
