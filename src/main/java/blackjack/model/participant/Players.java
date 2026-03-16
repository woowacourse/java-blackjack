package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        if (players == null) {
            throw new IllegalArgumentException("players가 null입니다.");
        }

        this.players = players;
    }

    public static Players of(
            List<String> names,
            Function<String, Player> nameConsumer
    ) {
        validateNames(names);

        return new Players(
                names.stream()
                        .map(nameConsumer)
                        .toList()
        );
    }

    private static void validateNames(List<String> names) {
        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }
    }

    public void pickInitialCards(CardDeck cardDeck) {
        if (cardDeck == null) {
            throw new IllegalArgumentException("카드덱이 null입니다.");
        }

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
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 null입니다.");
        }

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
        int playersTotalPrize = players.stream()
                .mapToInt(Player::getPrize)
                .sum();

        DealerProfit dealerProfit = new DealerProfit(playersTotalPrize);

        return dealerProfit.getAmount();
    }

    public void perform(Consumer<Player> consumer) {
        players.forEach(consumer);
    }
}
