package blackjack.domain.game;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

// TODO 리팩토링을 진지하게 깊이 많이많이 엄청 고민해보기
/*
1. 메서드 분리
2. 내부 로직 자체를 하나의 구현 클래스로 생성
3. enum이 아닌 인터페이스, 또는 추상 클래스로 선언 후 각각의 구현 클래스들을 사용

등등의 방법이 있어요 미래의 짱수님 ^^!
 */
public enum Result {
    PLAYER_BLACKJACK(
            (player, dealer) ->
                    player.isBlackjack() && !dealer.isBlackjack(),
            bettingMoney -> bettingMoney.multiply(1.5)
    ),
    PLAYER_WIN(
            (player, dealer) -> {
                if (player.isBusted()) {
                    return false;
                }
                if (dealer.isBusted()) {
                    return true;
                }
                if (!player.isBlackjack() && !dealer.isBusted()) {
                    return player.calculateScore() > dealer.calculateScore();
                }
                return false;
            }, Function.identity()),
    PUSH(((player, dealer) -> {
        if (player.isBusted() || dealer.isBusted()) {
            return false;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return true;
        }
        return player.calculateScore() == dealer.calculateScore();
    }), bettingMoney -> Money.ZERO),
    PLAYER_LOSE(((player, dealer) -> {
        if (player.isBusted() && !dealer.isBusted()) {
            return true;
        }
        return player.calculateScore() < dealer.calculateScore();
    }), bettingMoney -> bettingMoney.multiply(-1));

    private final BiPredicate<Player, Dealer> resultRule;

    //TODO 네이밍 + 다른 방법으로 리팩토링
    private final Function<Money, Money> prizeConsumer;

    Result(BiPredicate<Player, Dealer> resultRule, Function<Money, Money> prizeConsumer) {
        this.resultRule = resultRule;
        this.prizeConsumer = prizeConsumer;
    }

    public static Result of(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(result -> result.checkRule(player, dealer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private boolean checkRule(Player player, Dealer dealer) {
        return resultRule.test(player, dealer);
    }

    public Money getPrize(Money money) {
        return prizeConsumer.apply(money);
    }
}
