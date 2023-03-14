package blackjackgame.domain;

import static blackjackgame.domain.CardFixture.*;
import static blackjackgame.domain.card.CardValue.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Symbol;

class CardTest {
    @DisplayName("카드가 문양과 값을 가지고 생성되는지 확인한다")
    @Test
    void Should_Success_When_ConstructWithSymbolAndValue() {
        assertDoesNotThrow(() -> Card.of(Symbol.SPADE, FIVE));
    }

    @DisplayName("카드가 이미 존재하는 캐싱값과 같은지 확인한다")
    @Test
    void Should_Same_When_CreateSameCard() {
        Card diamond_ten = Card.of(Symbol.DIAMOND, TEN);
        assertThat(diamond_ten).isSameAs(Card.of(Symbol.DIAMOND, TEN));
    }

    @DisplayName("스페이스 5카드가 5의 점수를 가지는지 확인한다")
    @Test
    void Should_ReturnFive_When_FiveCard() {
        Card spadeFive = Card.of(Symbol.SPADE, FIVE);
        assertThat(spadeFive.score()).isEqualTo(new Score(5));
    }

    @DisplayName("카드가 에이스인지 확인한다.")
    @Test
    void Should_True_When_checkAce() {
        assertThat(SPADE_ACE.isAce()).isTrue();
        assertThat(HEART_ACE.isAce()).isTrue();
        assertThat(DIAMOND_ACE.isAce()).isTrue();
        assertThat(CLOVER_ACE.isAce()).isTrue();
        assertThat(CLOVER_FIVE.isAce()).isFalse();
    }
}
