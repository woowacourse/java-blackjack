package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJackService {

    private final Deck deck = new Deck();
    private Challengers challengers;
    private Dealer dealer;

    public void initChallengers(final List<String> requestNames) {
        List<Challenger> challengers = new ArrayList<>();
        for (String name : requestNames) {
            challengers.add(new Challenger(getInitCards(), new Name(name)));
        }
        this.challengers = new Challengers(challengers);
    }

    public void initDealer() {
        this.dealer = new Dealer(getInitCards());
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(challengers.toList());
        return players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Challengers getChallengers() {
        return this.challengers;
    }

    public void receiveMoreCard(final Player player) {
        player.receiveMoreCard(deck.draw());
    }

    private Cards getInitCards() {
        List<Card> cards = Arrays.asList(deck.draw(), deck.draw());
        return new Cards(cards);
    }
}
