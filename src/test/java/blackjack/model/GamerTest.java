package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @DisplayName("게이머의 카드 목록에 카드를 추가한다.")
    @Test
    void addCard() {
        //given
        Card card = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Gamer gamer = new Gamer();

        //when
        gamer.addCard(card);
        List<Card> gamerCards = gamer.getCards();

        //then
        assertThat(gamerCards).containsExactly(card);
    }

    @DisplayName("카드 목록을 추가할 때, 기존에 등록된 카드와 동일한 카드가 추가될 경우 예외를 발생시킨다.")
    @Test
    void validateCardDuplication_WhenAddCard() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Gamer gamer = new Gamer();

        //when
        gamer.addCard(card1);

        //then
        assertThatThrownBy(() -> gamer.addCard(card2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 목록을 추가할 때, 기존에 등록된 카드와 추가할 카드의 숫자 합을 계산한다.")
    @Test
    void calculateCardNumber_WhenAddCard() {
        //given
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.EIGHT);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.NINE);
        Gamer gamer = new Gamer();

        //when
        gamer.addCard(card1);
        gamer.addCard(card2);
        List<Card> cards = gamer.getCards();

        int sum = cards.stream()
                .mapToInt(Card::extractCardNumber)
                        .sum();
        //then
        assertThat(sum).isEqualTo(17);
    }
}
