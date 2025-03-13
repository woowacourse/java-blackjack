package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Betting;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    Participant player;

    @BeforeEach
    void initPlayer() {
        player = new Player("james", new Betting(10000));
    }

    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card card = new Card(Shape.HEART, Rank.A);

        // then
        player.addCard(card);

        // when
        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("모두에게 보여줄 플레이어 카드를 가져온다.")
    void should_return_public_able_cards() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.A));
        player.addCard(new Card(Shape.HEART, Rank.ONE));

        //when
        List<Card> shownCard = player.getShownCard();

        //then
        assertThat(shownCard).hasSize(2);
    }

    @Nested
    @DisplayName("가지고 있는 카드의 합계를 계산한다")
    class calculate_card_sum {

        @Test
        @DisplayName("KING, QUEEN, ONE 인 경우, 합은 21이어야 한다.")
        void given_king_queen_one_then_return_21() {
            player.addCard(new Card(Shape.HEART, Rank.KING));
            player.addCard(new Card(Shape.HEART, Rank.QUEEN));
            player.addCard(new Card(Shape.HEART, Rank.ONE));
            final int totalValue = player.getTotalValue();
            assertThat(totalValue).isEqualTo(21);
        }

        @Test
        @DisplayName("A, QUEEN인 경우, 합은 21이어야 한다.")
        void given_queen_a_then_return_21() {
            // given
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.QUEEN));

            // when
            final long totalValue = player.getTotalValue();

            // then
            assertThat(totalValue).isEqualTo(21);
        }

        @Test
        @DisplayName("A, QUEEN, ONE 인 경우, 합은 12이어야 한다.")
        void given_a_queen_heart_one_then_return_12() {
            //given
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.QUEEN));
            player.addCard(new Card(Shape.HEART, Rank.ONE));

            //when
            final int totalValue = player.getTotalValue();

            //then
            assertThat(totalValue).isEqualTo(12);
        }

        @Test
        @DisplayName("A, A 인 경우, 합은 12이어야 한다.")
        void given_a_and_a_then_return_12() {
            //given
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.SPADE, Rank.A));

            //when
            final int totalValue = player.getTotalValue();

            //then
            assertThat(totalValue).isEqualTo(12);
        }

        @Test
        @DisplayName("A, EIGHT, A 인 경우, 합은 20이어야 한다.")
        void given_a_eight_a_then_return_20() {
            //given
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.EIGHT));
            player.addCard(new Card(Shape.SPADE, Rank.A));

            //when
            final int totalValue = player.getTotalValue();

            //then
            assertThat(totalValue).isEqualTo(20);
        }

        @Test
        @DisplayName("A, A , A, TEN인 경우, 합은 13이어야 한다.")
        void given_a_and_a_and_a_and_ten_then_return_13() {
            //given
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.SPADE, Rank.A));
            player.addCard(new Card(Shape.CLUB, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.TEN));

            //when
            final int totalValue = player.getTotalValue();

            //then
            assertThat(totalValue).isEqualTo(13);
        }
    }

    /***
     * 플레이어 21 이하면 다시 입력받기 -> boolean true/ false
     * 딜러 16이하면 다시 뽑기
     */
    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 21 이하일 경우, true를 반환한다")
    void should_return_true_when_can_pick() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.KING));
        player.addCard(new Card(Shape.HEART, Rank.QUEEN));
        player.addCard(new Card(Shape.HEART, Rank.ONE));

        //when
        boolean canPick = player.canPick();

        //then
        assertThat(canPick).isTrue();
    }

    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 22 이상 경우, false를 반환한다")
    void should_return_false_when_cannot_pick() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.KING));
        player.addCard(new Card(Shape.HEART, Rank.QUEEN));
        player.addCard(new Card(Shape.HEART, Rank.TWO));

        //when
        boolean canPick = player.canPick();

        //then
        assertThat(canPick).isFalse();
    }
}
