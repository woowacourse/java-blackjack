package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public Dealer createDealer(Deck deck) {
        Dealer dealer = new Dealer();
        dealer.hit(deck.pick());
        dealer.hit(deck.pick());
        return dealer;
    }

    public Players createPlayers(List<String> names, Deck deck) {
        Players players = new Players(names.stream()
                .map(Name::new)
                .map(Player::from)
                .collect(Collectors.toList()));
        players.initCards(deck);
        return players;
    }
}
