package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;

class CardTest {
    @DisplayName("카드가 문양과 값을 가지고 생성되는지 확인한다")
    @Test
    void Should_Success_When_ConstructWithSymbolAndValue() {
        assertDoesNotThrow(() -> new Card(Symbol.SPADE, CardValue.FIVE));
    }

    @DisplayName("스페이스 5카드가 5의 점수를 가지는지 확인한다")
    @Test
    void Should_ReturnFive_When_FiveCard() {
        Card spadeFive = new Card(Symbol.SPADE, CardValue.FIVE);
        assertThat(spadeFive.getScore()).isEqualTo(CardValue.FIVE.getScore());
    }

    @DisplayName("카드가 에이스인지 확인한다.")
    @Test
    void Should_True_When_checkAce() {
        Card spadeAce = new Card(Symbol.SPADE, CardValue.ACE);
        Card heartAce = new Card(Symbol.HEART, CardValue.ACE);
        Card diamondAce = new Card(Symbol.DIAMOND, CardValue.ACE);
        Card cloverAce = new Card(Symbol.CLOVER, CardValue.ACE);
        Card spadeFive = new Card(Symbol.SPADE, CardValue.FIVE);

        assertThat(spadeAce.isAce()).isTrue();
        assertThat(heartAce.isAce()).isTrue();
        assertThat(diamondAce.isAce()).isTrue();
        assertThat(cloverAce.isAce()).isTrue();
        assertThat(spadeFive.isAce()).isFalse();
    }
}
