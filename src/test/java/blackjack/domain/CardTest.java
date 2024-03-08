package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카드")
public class CardTest {

    @DisplayName("숫자와 기호를 가진 카드 조회에 성공한다.")
    @Test
    void createCard() {
        //given
        Rank rank = Rank.FOUR;
        Suit suit = Suit.CLOVER;

        //when
        Card card = new Card(rank, suit);

        //then
        assertThat(card.getRank())
                .isEqualTo(rank);
        assertThat(card.getSuit())
                .isEqualTo(suit);
    }
}
