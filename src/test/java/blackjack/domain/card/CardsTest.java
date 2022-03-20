package blackjack.domain.card;


import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_NINE;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("카드 목록 합계 테스트")
    public void cardsSumTest() {
        Cards cards = new Cards(List.of(SPADE_TWO, SPADE_JACK));

        assertThat(cards.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("Ace 포함 카드 목록 합계 테스트")
    public void cardsSumHasAceTest() {
        Cards cards = new Cards(List.of(SPADE_ACE, SPADE_JACK));

        assertThat(cards.sum()).isEqualTo(21);
    }

    @Test
    @DisplayName("21점 초과시 Ace 1점으로 계산 테스트")
    public void cardsSumHasAceMoreThan21Test() {
        Cards cards = new Cards(List.of(SPADE_TWO, SPADE_JACK));
        cards = cards.add(SPADE_ACE);
        assertThat(cards.sum()).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 포함하고 있는지 테스트")
    public void hasAceTest() {
        Cards cards = new Cards(List.of(SPADE_ACE, SPADE_JACK));

        assertThat(cards.hasAce()).isEqualTo(true);
    }

    @Test
    @DisplayName("Ace가 없는지 테스트")
    public void hasNotAceTest() {
        Cards cards = new Cards(List.of(SPADE_TWO, SPADE_JACK));

        assertThat(cards.hasAce()).isEqualTo(false);
    }

    @Test
    @DisplayName("처음 두장이 21점인 경우 BlackJack 테스트")
    public void isBlackJackTest() {
        Cards cards = new Cards(List.of(SPADE_ACE, SPADE_JACK));

        assertThat(cards.isBlackJack()).isEqualTo(true);
    }

    @Test
    @DisplayName("세 장의 합이 21점인 경우 Non-BlackJack 테스트")
    public void isNonBlackJackTest() {
        Cards cards = new Cards(List.of(SPADE_NINE, SPADE_JACK));
        cards = cards.add(SPADE_TWO);
        assertThat(cards.isBlackJack()).isEqualTo(false);
    }

    @Test
    @DisplayName("카드 한 장 받았을 때 Ready 상태인지 테스트")
    public void isReadyTest() {
        Cards cards = new Cards();
        cards = cards.add(SPADE_TWO);
        assertThat(cards.isNotInitialized()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 두 장 받았을 때 Non-Ready 상태인지 테스트")
    public void isNonReadyTest() {
        Cards cards = new Cards();
        cards = cards.add(SPADE_TWO);
        cards = cards.add(SPADE_NINE);
        assertThat(cards.isNotInitialized()).isEqualTo(false);
    }

    @Test
    @DisplayName("21점 초과시 Bust 테스트")
    public void isBustTest() {
        Cards cards = new Cards(List.of(SPADE_NINE, SPADE_NINE));

        cards = cards.add(SPADE_JACK);
        assertThat(cards.isBust()).isEqualTo(true);
    }

    @Test
    @DisplayName("21점 이하 시 Non-Bust 테스트")
    public void isNonBustTest() {
        Cards cards = new Cards(List.of(SPADE_NINE, SPADE_NINE));

        cards = cards.add(SPADE_TWO);
        assertThat(cards.isBust()).isEqualTo(false);
    }
}
