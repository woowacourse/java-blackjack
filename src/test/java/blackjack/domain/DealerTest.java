package blackjack.domain;

import static blackjack.domain.CardsTestDataGenerator.*;
import static blackjack.domain.Denomination.*;
import static blackjack.domain.GameResult.*;
import static blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.cards.CardsArgumentsProvider;
import java.sql.PreparedStatement;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DealerTest {

    @DisplayName("카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 딜러_생성_정상() {
        assertDoesNotThrow(() -> new Dealer(generateCards(), 0));
    }

    @DisplayName("딜러의 점수가 16점 이하인 경우 카드를 받을 수 있다.")
    @Test
    void 카드_발급_가능() {
        List<Card> cards = generateTotalScoreNotMoreThan16Cards();
        Dealer dealer = new Dealer(cards, 0);

        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점을 초과한 경우 카드를 받을 수 없다.")
    @Test
    void 카드_발급_불가능() {
        List<Card> cards = generateTotalScoreGraterThan17Cards();
        Dealer dealer = new Dealer(cards, 0);

        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        Dealer dealer = new Dealer(generateCards(), 0);
        Card card = Card.of(FIVE, SPADE);

        dealer.addCard(card);

        assertThat(dealer.getCards().getValue().size()).isEqualTo(3);
    }

    @DisplayName("Cards가 주어지면 점수를 계산하면 반환한다.")
    @ParameterizedTest
    @ArgumentsSource(CardsArgumentsProvider.class)
    void 딜러_카드_점수_계산(Cards cards, int totalScore) {
        Dealer dealer = new Dealer(cards.getValue(), 0);

        assertThat(dealer.getTotalScore()).isEqualTo(totalScore);
    }

    @DisplayName("딜러가 이겼을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_승리() {
        Dealer dealer = new Dealer(generateCards(), 0);
        Player player = new Player("sudal", 1000, generateTotalScoreGraterThan17Cards());

        player.calculateBattingMoneyResult(dealer);
        dealer.calculateBattingMoneyResult(player);

        assertThat(dealer.getBattingMoney()).isEqualTo(1000);
    }

    @DisplayName("딜러가 졌을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_패배() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards(), 0);
        Player player = new Player("sudal", 1000, generateTotalScoreGraterThan17Cards());

        player.calculateBattingMoneyResult(dealer);
        dealer.calculateBattingMoneyResult(player);

        assertThat(dealer.getBattingMoney()).isEqualTo(-1000);
    }

    @DisplayName("플레이어가 블랙잭이라서 딜러가 졌을 때 최종 배팅 머니 계산")
    @Test
    void 배팅금액_합계_블랙잭_패배() {
        Dealer dealer = new Dealer(generateTotalScoreNotMoreThan16Cards(), 0);
        Player player = new Player("sudal", 1000, generateCards());

        player.calculateBattingMoneyResult(dealer);
        dealer.calculateBattingMoneyResult(player);

        assertThat(dealer.getBattingMoney()).isEqualTo(-2000);
    }

    @DisplayName("플레이어와 딜러 무승부일 때 배팅 머니 계산")
    @Test
    void 배팅금액_합계_무승부() {
        Dealer dealer = new Dealer(generateCards(), 0);
        Player player = new Player("sudal", 1000, generateCards());

        player.calculateBattingMoneyResult(dealer);
        dealer.calculateBattingMoneyResult(player);

        assertThat(dealer.getBattingMoney()).isEqualTo(0);
    }
}
