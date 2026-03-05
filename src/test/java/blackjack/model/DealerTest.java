package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑아서 핸즈에 추가한다.")
    void pickACard() {
        // given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.init();

        // when & then
        assertThatCode(() -> dealer.pickACard(cardDeck))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.init();

        for (int i = 0; i < 16; i++) {
            dealer.pickACard(cardDeck);
        }

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러의 총 점수가 21 초과이면 true를 반환한다.")
    void isBust() {
        // given
        CardDeck cardDeck = CardDeck.init();

        Dealer dealer = Dealer.create();

        for (int i = 0; i < 21; i++) {
            dealer.pickACard(cardDeck);
        }

        // when & then
        assertThat(dealer.isBust()).isTrue();
    }
}