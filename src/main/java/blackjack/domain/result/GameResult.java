package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    BLACKJACK_WIN((dealer, player) -> player.isBlackjack() && !dealer.isBlackjack()),
    WIN(((dealer, player) -> dealer.isBust() && player.isStand())),
    LOSE((dealer, player) -> player.isBust() || (player.isStand() && dealer.isBlackjack())),
    DRAW((dealer, player) -> player.isBlackjack() && dealer.isBlackjack());

    GameResult(BiPredicate<Dealer, Player> predicate) {
        this.predicate = predicate;
    }

    private final BiPredicate<Dealer, Player> predicate;

    public static GameResult createPlayerResult(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.matches(dealer, player))
                .findAny()
                .orElse(GameResult.findByScore(dealer, player));
    }

    public boolean matches(Dealer dealer, Player player) {
        return predicate.test(dealer, player);
    }

    public static GameResult findByScore(Dealer dealer, Player player) {
        if (dealer.getScore() < player.getScore()) {
            return WIN;
        }

        if (dealer.getScore() > player.getScore()) {
            return LOSE;
        }

        return DRAW;
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }
}
