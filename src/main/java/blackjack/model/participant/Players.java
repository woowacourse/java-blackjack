package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(
            List<String> names,
            Function<String, Player> nameConsumer
    ) {
        return new Players(
                names.stream()
                        .map(nameConsumer)
                        .toList()
        );
    }

    public void pickInitialCards(CardDeck cardDeck) {
        players.forEach(
                player -> player.pickInitialCards(cardDeck)
        );
    }

    public List<String> getNames() {
        return players.stream()
                .map(Participant::getName)
                .toList();
    }

    public Players applyBlackjack() {
        return new Players(
                players.stream()
                        .map(player -> {
                            if (player.isBlackjack()) {
                                return player.blackjack();
                            }

                            return player;
                        }).toList()
        );
    }

    public Players applyBust() {
        return new Players(
                players.stream()
                        .map(player -> {
                            if (player.isBust()) {
                                return player.lose();
                            }

                            return player;
                        }).toList()
        );
    }

    public Players award(Dealer dealer) {
        if (dealer.isBust()) {
            return this;
        }

        return new Players(
                players.stream()
                        .map(player -> awardPlayer(
                                player,
                                dealer.getCurrentTotalScore()
                        )).toList()
        );
    }

    private Player awardPlayer(
            Player player,
            int dealerScore
    ) {
        if (player.getCurrentTotalScore() >= dealerScore) {
            return player;
        }

        return player.lose();
    }

    public int getDealerProfit() {
        int negativeMultiplier = -1;

        return negativeMultiplier * players.stream()
                .mapToInt(Player::getPrize)
                .sum();
    }

    public void perform(Consumer<Player> consumer) {
        players.forEach(consumer);
    }
}
