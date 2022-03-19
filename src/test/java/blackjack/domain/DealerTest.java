package blackjack.domain;

import static blackjack.domain.CardsTestDataGenerator.*;
import static blackjack.domain.Denomination.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.cards.CardsArgumentsProvider;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DealerTest {

    @DisplayName("카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 딜러_생성_정상() {
        assertDoesNotThrow(() -> new Dealer(generateBlackJackCards()));
    }

    @DisplayName("딜러의 점수가 16점 이하인 경우 카드를 받을 수 있다.")
    @Test
    void 카드_발급_가능() {
        List<Card> cards = generateTotalScoreNotMoreThan16Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점을 초과한 경우 카드를 받을 수 없다.")
    @Test
    void 카드_발급_불가능() {
        List<Card> cards = generateTotalScoreGraterThan17Cards();
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        Dealer dealer = new Dealer(generateBlackJackCards());
        Card card = Card.of(FIVE, SPADE);

        dealer.addCard(card);

        assertThat(dealer.getCards().getValue().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 딜러_카드_점수_계산(Cards cards, int totalScore) {
        Dealer dealer = new Dealer(cards.getValue());

        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }

    @DisplayName("딜러가 이겼을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_승리() {
        Dealer dealer = new Dealer(generateBlackJackCards());
        Player player = new Player("sudal", new BettingMoney(1000), generateTotalScoreGraterThan17Cards());

        player.changeByBettingMoneyResult(dealer);
        dealer.changeByBettingMoneyResult(player);

        assertThat(dealer.getBettingMoney()).isEqualTo(1000);
    }

    @DisplayName("딜러가 졌을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_패배() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards());
        Player player = new Player("sudal", new BettingMoney(1000), generateTotalScoreGraterThan17Cards());

        player.changeByBettingMoneyResult(dealer);
        dealer.changeByBettingMoneyResult(player);

        assertThat(dealer.getBettingMoney()).isEqualTo(-1000);
    }

    @DisplayName("플레이어가 블랙잭이라서 딜러가 졌을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_블랙잭_패배() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards());
        Player player = new Player("sudal", new BettingMoney(1000), generateBlackJackCards());

        player.changeByBettingMoneyResult(dealer);
        dealer.changeByBettingMoneyResult(player);

        assertThat(dealer.getBettingMoney()).isEqualTo(-1500);
    }

    @DisplayName("플레이어와 딜러가 블랙잭 무승부일 때 배팅 머니 계산")
    @Test
    void 배팅금액_합계_블랙잭_무승부() {
        Dealer dealer = new Dealer(generateBlackJackCards());
        Player player = new Player("sudal", new BettingMoney(1000), generateBlackJackCards());

        player.changeByBettingMoneyResult(dealer);
        dealer.changeByBettingMoneyResult(player);

        assertThat(dealer.getBettingMoney()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러가 무승부일 때 배팅 머니 계산")
    @Test
    void 배팅금액_합계_무승부() {
        Dealer dealer = new Dealer(generate21Cards());
        Player player = new Player("sudal", new BettingMoney(1000), generate21Cards());

        player.changeByBettingMoneyResult(dealer);
        dealer.changeByBettingMoneyResult(player);

        assertThat(dealer.getBettingMoney()).isEqualTo(0);
    }
}
