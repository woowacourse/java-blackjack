package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.ResultDTO;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final User dealer;
    private final List<User> players;

    public Game(List<String> names) {
        dealer = new Dealer();
        players = createPlayer(names);
    }

    private List<User> createPlayer(List<String> names) {
        return names.stream()
            .map(Player::create)
            .collect(Collectors.toList());
    }

    public void initialCards(Deck deck) {
        dealer.initialHands(deck.pickInitialCards());
        players.forEach(player -> player.initialHands(deck.pickInitialCards()));
    }

    public boolean askDrawToDealer(Deck deck) {
        if(!dealer.isHit()) {
            return false;
        }

        while(dealer.isHit()) {
            dealer.draw(deck.pickSingleCard());
        }
        return true;
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }

    public List<ResultDTO> getResultDTOs() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        resultDTOS.add(dealer.getResultDTO());
        players.forEach(player -> resultDTOS.add(player.getResultDTO()));

        return resultDTOS;
    }
}
