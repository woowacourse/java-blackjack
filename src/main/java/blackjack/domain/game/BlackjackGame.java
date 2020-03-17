package blackjack.domain.game;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BlackjackGame {

    private Users users;
    private Deck deck;

    public BlackjackGame(Users users, Deck deck) {
        Objects.requireNonNull(users, "users가 null입니다");
        Objects.requireNonNull(deck, "deck이 null입니다");
        this.users = users;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        users.getUsers()
                .forEach(t -> t.receiveInitialCards(deck.drawInitialCards()));
    }

    public Map<Player, Result> calculateAllResult(Users users) {
        Dealer dealer = users.getDealer();
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        users.getPlayer()
                .forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        return totalResult;
    }

}