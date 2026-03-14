package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class DeckTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    @DisplayName("덱을 셔플하지 않은 상태에서, 가장 첫 번째 카드를 리턴한다.")
    void 셔플_하지_않은_덱_딜_카드_테스트() {
        // given
        Deck deck = new Deck();
        Card expectedFirstCard = new Card(Suit.SPADE, Rank.ACE);

        // when
        Card drawnCard = deck.drawCard();

        // then
        assertThat(drawnCard).isEqualTo(expectedFirstCard);
    }

    @Test
    @DisplayName("카드를 뽑는 경우, 덱에 남은 카드의 수가 1장 줄어든다.")
    void 덱에서_카드_뽑기_테스트() {
        // given
        Deck deck = new Deck();

        // when
        deck.drawCard();

    }

    @Test
    @DisplayName("카드를 뽑는 횟수가 52회 초과인 경우, NoSuchElementException이 발생한다.")
    void 카드_뽑기_52회_초과_예외_테스트() {
        // given
        Deck deck = new Deck();

        //  52회까지 정상 뽑기 가능
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
