package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        assertAll(
                () -> assertThat(card.getRank()).isEqualTo(rank),
                () -> assertThat(card.getSuit()).isEqualTo(suit)
        );
    }
}
