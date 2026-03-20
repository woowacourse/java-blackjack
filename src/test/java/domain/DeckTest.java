package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class DeckTest {
    @Test
    @DisplayName("덱은 중복이 아닌, 52장의 카드로 생성된다.")
    void 덱_52장_중복_없음_테스트() {
        // given
        Deck deck = new Deck(new NoRandomShuffleStrategy());
        Set<Card> cards = new HashSet<>();

        // when
        for (int i = 0; i < 52; i++) {
            cards.add(deck.drawCard());
        }

        // then
        assertThat(cards).hasSize(52);
    }

    @Test
    @DisplayName("덱을 셔플하지 않은 상태에서, 가장 첫 번째 카드를 리턴한다.")
    void 셔플_하지_않은_덱_딜_카드_테스트() {
        // given
        Deck deck = new Deck(new NoRandomShuffleStrategy());
        Card expectedFirstCard = new Card(Suit.SPADE, Rank.ACE);

        // when
        deck.shuffleCards();
        Card drawnCard = deck.drawCard();

        // then
        assertThat(drawnCard).isEqualTo(expectedFirstCard);
    }

    @Test
    @DisplayName("카드를 뽑는 횟수가 52회 초과인 경우, NoSuchElementException이 발생한다.")
    void 카드_뽑기_52회_초과_예외_테스트() {
        // given
        Deck deck = new Deck(new NoRandomShuffleStrategy());
        deck.shuffleCards();

        //  52회까지 정상 뽑기 가능
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("[ERROR] 더 이상의 카드를 꺼낼 수 없습니다.");
    }
}
