package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    @DisplayName("전달된 카드 리스트에 카드가 2개 미만인 경우 예외를 던진다.")
    void createCardsLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Cards(List.of(Deck.CLOVER_ACE)))
                .withMessage("카드는 두 장 이상이어야 합니다.");
    }

    // TODO: 이름 바꾸기
    @Test
    @DisplayName("카드를 추가할 수 있다.")
    void createPlayerWithCards() {
        Cards cards = new Cards(List.of(Deck.CLOVER_FIVE, Deck.CLOVER_FOUR));
        Cards addedCard = cards.addCard(Deck.CLOVER_ACE);
        assertThat(addedCard.getCards()).isEqualTo(List.of(Deck.CLOVER_FIVE, Deck.CLOVER_FOUR, Deck.CLOVER_ACE));
    }

    @Test
    @DisplayName("카드의 합을 계산할 수 있다.")
    void calculateScore() {
        Cards cards = new Cards(List.of(Deck.CLOVER_FIVE, Deck.CLOVER_FOUR));
        assertThat(cards.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Ace를 여러 개 가진 경우 동일한 값인 1로 변경된다.")
    void calculateScoreWithAces() {
        Cards cards = new Cards(List.of(Deck.SPADE_ACE, Deck.DIA_ACE));
        assertThat(cards.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ace의 기본값으로 결과를 계산했을 때 21을 초과한 경우 Ace 값은 1로 변경된다.")
    void calculateScoreWithAce() {
        Cards cards = new Cards(List.of(Deck.CLOVER_JACK, Deck.CLOVER_TWO, Deck.SPADE_ACE));
        assertThat(cards.calculateScore()).isEqualTo(13);
    }
}
