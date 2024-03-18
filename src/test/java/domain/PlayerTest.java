package domain;

import static domain.BetAmountFixture.betAmount10_000;
import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player(new Name("pobi"), Hands.createEmptyHands(), betAmount10_000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참여자 이름이 딜러이면 예외가 발생한다.")
    void validateName() {
        Assertions.assertThatThrownBy(() -> new Player(new Name("딜러"), Hands.createEmptyHands(), betAmount10_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 사용할 수 없는 이름입니다.");
    }

    @DisplayName("참여자가 블랙잭이면서 승리하면 배팅 금액의 1.5배를 받는다.")
    @Test
    void winBlackJack() {
        // given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum20Size2);
        final Player player = new Player(new Name("제제"), blackJack, betAmount10_000);

        // when
        final Profit profit = player.calculateProfitBy(dealer);

        // then
        Assertions.assertThat(profit).isEqualTo(new Profit((int) (10_000 * 1.5)));
    }

    @DisplayName("참여자가 블랙잭이 아니면서 승리하면 배팅 금액의 1배를 받는다.")
    @Test
    void win() {
        // given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum18Size2);
        final Player player = new Player(new Name("제제"), sum20Size3, betAmount10_000);

        // when
        final Profit profit = player.calculateProfitBy(dealer);

        // then
        Assertions.assertThat(profit).isEqualTo(new Profit(10_000));
    }

    @DisplayName("참여자가 패배하면 배팅 금액을 모두 잃는다.")
    @Test
    void lose() {
        // given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum20Size2);
        final Player player = new Player(new Name("제제"), bustHands, betAmount10_000);

        // when
        final Profit profit = player.calculateProfitBy(dealer);

        // then
        Assertions.assertThat(profit).isEqualTo(new Profit((-1) * 10_000));
    }

    @DisplayName("무승부이면 금액을 돌려받는다.")
    @Test
    void tie() {
        final Dealer dealer = new Dealer(CardDeck.generate(), sum21Size2);
        final Player player = new Player(new Name("제제"), sum21Size2, betAmount10_000);

        // when
        final Profit profit = player.calculateProfitBy(dealer);

        // then
        Assertions.assertThat(profit).isEqualTo(new Profit(0));
    }
}
