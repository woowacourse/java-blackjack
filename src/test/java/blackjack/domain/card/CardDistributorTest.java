package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static utils.TestUtil.CLOVER_ACE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDistributorTest {

    @Test
    @DisplayName("생성 확인")
    void distribute() {
        // given
        CardDistributor cardDistributor = new CardDistributor(new RandomGenerator());

        // then
        assertThatNoException().isThrownBy(cardDistributor::distribute);
    }

    @Test
    @DisplayName("카드가 다 소요되면 에러가 발생한다.")
    void failed() {
        // given
        TestGenerator testGenerator = new TestGenerator(CLOVER_ACE);
        CardDistributor cardDistributor = new CardDistributor(testGenerator);

        // when
        cardDistributor.distribute();

        // then
        assertThatThrownBy(cardDistributor::distribute)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("카드가 모두 소요됐습니다.");
    }

    @Test
    @DisplayName("카드 꺼내기")
    void draw() {
        // given
        Card card = CLOVER_ACE;
        TestGenerator testGenerator = new TestGenerator(card);
        CardDistributor cardDistributor = new CardDistributor(testGenerator);

        // when
        Card cardFromDeck = cardDistributor.distribute();

        // then
        assertThat(cardFromDeck).isEqualTo(card);
    }
}
