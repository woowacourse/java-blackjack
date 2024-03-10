package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카드")
public class TrumpCardTest {

    @DisplayName("숫자와 기호를 가진 카드 조회에 성공한다.")
    @Test
    void createCard() {
        //given
        Rank rank = Rank.FOUR;
        Suit suit = Suit.CLOVER;

        //when
        TrumpCard trumpCard = new TrumpCard(rank, suit);

        //then
        assertAll(
                () -> assertThat(trumpCard.getRank()).isEqualTo(rank),
                () -> assertThat(trumpCard.getSuit()).isEqualTo(suit)
        );
    }
}
