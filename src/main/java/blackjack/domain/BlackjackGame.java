package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultBoard;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final String YES = "y";
    private static final String NO = "n";

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

    public static BlackjackGame generateByUser(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = Players.of(names);
        return new BlackjackGame(dealer, players);
    }

    public void handOutInitialCards() {
        dealer.initializeCards(deck.popToInitialCards());
        players.getPlayers()
                .forEach(player -> player.initializeCards(deck.popToInitialCards()));
    }

    public boolean isNotGameOver(User user) {
        return user.isAbleToHit();
    }

    public void hit(User user) {
        user.hit(deck.popOne());
    }

    public ResultBoard generateResultBoard() {
        return ResultBoard.of(players, dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players.getPlayers());
        return users;
    }

    public void proceedPlayersRound(Player player, String answer) {
        validateAnswer(answer);
        if (YES.equals(answer)) {
            player.hit(deck.popOne());
        }
        if (NO.equals(answer)) {
            player.stay();
        }
    }

    private void validateAnswer(String answer) {
        if (YES.equals(answer) || NO.equals(answer)) {
            return;
        }
        throw new IllegalArgumentException("y, n로만 대답할 수 있습니다.");
    }
}
