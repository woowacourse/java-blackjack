package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

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
            List<Integer> betAmounts
    ) {
        validate(names, betAmounts);

        List<Player> players = new ArrayList<>();

        for (int index = 0; index < names.size(); index++) {
            players.add(Player.of(names.get(index), betAmounts.get(index)));
        }

        return new Players(players);
    }

    private static void validate(
            List<String> names,
            List<Integer> betAmounts
    ) {
        if (names == null) {
            throw new IllegalArgumentException("names가 null입니다.");
        }

        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }

        if (betAmounts == null) {
            throw new IllegalArgumentException("betAmounts가 null입니다.");
        }

        if (betAmounts.size() != names.size()) {
            throw new IllegalArgumentException("이름과 배팅 금액의 개수가 서로 다릅니다.");
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
