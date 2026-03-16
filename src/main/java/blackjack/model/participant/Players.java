package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import java.util.HashSet;
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
        validate(names);

        return new Players(
                names.stream()
                        .map(nameConsumer)
                        .toList()
        );
    }

    private static void validate(List<String> names) {
        String blank = " ";
        boolean hasInvalidName = names.stream()
                .anyMatch(name -> name.startsWith(blank) || name.endsWith(blank));

        if (hasInvalidName) {
            throw new IllegalArgumentException("이름이 공백으로 시작하거나 끝납니다.");
        }

        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }
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
