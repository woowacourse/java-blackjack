package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BettingResultTest {

    @ParameterizedTest(name = "플레이어 {0}, 딜러 {1}, 플레이어 베팅금액 {2}, 딜러 베팅금액 {3} 일 때, 플레이어는 금액이 {4}, 딜러는 {5}이 된다.")
    @MethodSource("fixture.BettingResultTestFixture#플레이어의_상황별_베팅금액_정보제공")
    void 다양한_게임_상황에서_베팅_결과가_올바르게_적용된다(
            List<Card> playerCards,
            List<Card> dealerCards,
            long playerStartMoney,
            long playerResultMoney,
            long dealerResultMoney
    ) {
        // given
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);
        dealer.checkBlackJack();

        Player player = new Player("pobi");
        playerCards.forEach(player::addCard);
        player.setMoney(playerStartMoney);
        player.checkBlackJack();

        // when
        BettingResult bettingResult = new BettingResult();
        bettingResult.calculateBettingMoney(dealer, List.of(player));
        long playerMoney = player.profit();
        long dealerMoney = dealer.profit();

        // then
        assertAll(
                () -> assertThat(playerMoney).isEqualTo(playerResultMoney),
                () -> assertThat(dealerMoney).isEqualTo(dealerResultMoney)
        );
    }
}
