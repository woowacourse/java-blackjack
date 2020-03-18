package blackjack.domain.game;

import blackjack.domain.Result;
import blackjack.domain.TotalResult;
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

    public TotalResult calculateAllResult(Users users) {
        Dealer dealer = users.getDealer();
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        users.getPlayer()
                .forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        return new TotalResult(totalResult);
    }

    public boolean decideDealerToHitCard() {
        Dealer dealer = users.getDealer();
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            return true;
        }
        return false;
    }
}