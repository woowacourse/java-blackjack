package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.*;

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

    public void drawInitialCards(Deck deck) {
        dealer.initialHands(deck.pickInitialCards());
        players.forEach(player -> player.initialHands(deck.pickInitialCards()));
    }

    public boolean addCardToDealer(Deck deck) {
        if (!dealer.isHit()) {
            return false;
        }

        while (dealer.isHit()) {
            dealer.draw(deck.pickSingleCard());
        }
        return true;
    }

    public List<ResultDTO> getResultDTOs() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        resultDTOS.add(dealer.getResultDTO());
        players.forEach(player -> resultDTOS.add(player.getResultDTO()));

        return resultDTOS;
    }

    public List<WinningResultDTO> getWinningResultDTOs() {
        return players.stream().map(player ->
            new WinningResultDTO(player.getName(), MatchResult.calculateResult(player, dealer)))
            .collect(Collectors.toList());
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }
}
