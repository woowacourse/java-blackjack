package blackjack.domain.user;

import blackjack.domain.result.Result;

import java.util.Arrays;

public class Player extends User {
    private static final int HIT = 21;

    private final double money;

    public Player(String name, double bettingMoney) {
        super(new Name(name));
        this.money = bettingMoney;
    }

    //- 버스트
    //    - 딜러가 버스트인 경우: 플레이어가 버스트가 아닌 경우 플레이어가 승리한다. 플레이어가 블랙잭인 경우 금액의 1.5배를 얻는다.
    //    - 플레이어가 버스트인 경우: 딜러의 패와 상관 없이 딜러가 승리한다.
    //  - 블랙잭
    //    - 딜러와 플레이어 모두 블랙잭인 경우: 비긴다.
    //    - 딜러가 블랙잭인 경우: 딜러가 승리한다. 플레이어는 본인이 걸었던 금액을 잃는다.
    //    - 플레이어가 블랙잭인 경우: 플레이어가 승리한다. 딜러는 금액의 1.5배를 잃는다.
    public Result decide(Dealer dealer) {
//        if (isBust()) {
//            return Result.LOSE;
//        }
//        if (dealer.isBust() && !isBust()) {
//            return Result.WIN;
//        }
//        if (dealer.isBlackjack() && isBlackjack()) {
//            return Result.STANDOFF;
//        }
//        if (dealer.isBlackjack() && !isBlackjack()) {
//            return Result.LOSE;
//        }
//        if (!dealer.isBlackjack() && isBlackjack()) {
//            return Result.WIN;
//        }
//        return Arrays.stream(Result.values())
//                .filter(value -> value.getCompareResult() == this.cards.compareTo(dealer.cards))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);

        if (isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() && !isBust()) {
            return Result.WIN;
        }
        return Arrays.stream(Result.values())
                .filter(value -> value.getCompareResult() == this.cards.compareTo(dealer.cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore().getScore() <= HIT;
    }
}
