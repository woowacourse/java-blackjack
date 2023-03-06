package domain.player;


import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void beforeEach() {
        //given
        player = Participant.from("준팍");
        player.takeCard(Card.of(Suit.DIAMOND, Rank.FIVE));
        player.takeCard(Card.of(Suit.SPADE, Rank.TWO));
        player.takeCard(Card.of(Suit.CLUBS, Rank.SIX));
    }

    @Test
    @DisplayName("카드를 출력한다")
    void createParticipant_thenDisplayCards() {
        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards)
                .isEqualTo(List.of(Card.of(Suit.DIAMOND, Rank.FIVE)
                        , Card.of(Suit.SPADE, Rank.TWO)
                        , Card.of(Suit.CLUBS, Rank.SIX)));
    }

    @Nested
    class IsBustTest {
        @Test
        @DisplayName("카드 점수의 총합이 22 미만이면 true를 반환한다.")
        void giveUnderBustScore_thenReturnTrue() {
            //given
            player.takeCard(Card.of(Suit.SPADE, Rank.EIGHT));

            //when
            final boolean isBust = player.isBust();

            //then
            assertThat(isBust).isFalse();
        }

        @Test
        @DisplayName("카드 점수의 총합이 22 이상이면 false를 반환한다.")
        void giveBustScore_thenReturnFalse() {
            //given
            player.takeCard(Card.of(Suit.SPADE, Rank.NINE));

            //when
            final boolean isBust = player.isBust();

            //then
            assertThat(isBust).isTrue();
        }
    }

}
