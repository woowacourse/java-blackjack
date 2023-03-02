package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardsTest {

    @BeforeEach
    void init() {
        Cards.init(CardFactory.of());
    }

    @Test
    @DisplayName("카드개수가 48개가 아니라면 예외처리한다.")
    void cards_init_validate_test() {
        // given & when
        List<Card> cards = CardFactory.of();
        cards.remove(0);

        // then
        Assertions.assertThatThrownBy(() -> Cards.init(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드의 개수는 총 48개여야 합니다.");
    }

    @Test
    @DisplayName("giveFirstCard()는 첫번째 카드를 반환한다")
    void give_first_card_test() {
        // then
        Assertions.assertThat(Cards.giveFirstCard().getNumber()).isEqualTo(CardNumber.ACE);
        Assertions.assertThat(Cards.giveFirstCard().getSymbol()).isEqualTo(CardSymbol.HEART);
    }

    @Test
    @DisplayName("giveFirstCard()는 카드가 비어있으면 예외를 발생시킨다.")
    void give_first_card_empty_test() {
        // given & when
        for(int i=0;i<48;i++){
            Cards.giveFirstCard();
        }
        // then
        Assertions.assertThatThrownBy(Cards::giveFirstCard)
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("뽑을 수 있는 카드가 없습니다.");
    }

}
