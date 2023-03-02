package domain;

import static domain.GameResult.comparePlayerWithDealer;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {

    private final Users users;
    private final Deck deck;

    public BlackJack(final Users users, final CardIndexGenerator cardIndexGenerator) {
        this.users = users;
        this.deck = Deck.from(cardIndexGenerator);
    }

    public Map<Player, GameResult> calculateGameResults() {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        int dealerScore = dealer.getScore();
        return players.stream()
            .collect(
                Collectors.toMap(player -> player, player -> comparePlayerWithDealer(player.getScore(), dealerScore)));
    }
}
