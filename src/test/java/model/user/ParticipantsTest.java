package model.user;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.SPADE;
import static model.card.Value.FIVE;
import static model.card.Value.FOUR;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.THREE;
import static model.card.Value.TWO;
import static model.user.Result.LOSE;
import static model.user.Result.TIE;
import static model.user.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;
    private Dealer dealer;
    private Player bebe;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        participants = Participants.of(List.of("bebe"), dealer);
        bebe = participants.getPlayers().get(0);
    }

    @Test
    @DisplayName("플레이어의 게임 결과가 반환된다.")
    void getPlayerFinalResult() {
        // given
        dealer.receiveCard(new Card(SPADE, FIVE));
        bebe.receiveCard(new Card(SPADE, FOUR));

        // when, then
        assertAll(
                () -> assertThat(participants.findPlayerFinalResult().get(bebe)).isEqualTo(LOSE),
                () -> {
            bebe.receiveCard(new Card(CLOVER, TWO));
            assertThat(participants.findPlayerFinalResult().get(bebe)).isEqualTo(WIN);
        });
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 21 초과일 경우 플레이어가 지는 결과가 반환된다.")
    void whenOverBothBustNumber_thenReturnLose() {
        // given
        dealer.receiveCard(new Card(SPADE, KING));
        dealer.receiveCard(new Card(DIAMOND, KING));
        dealer.receiveCard(new Card(CLOVER, THREE));

        bebe.receiveCard(new Card(SPADE, JACK));
        bebe.receiveCard(new Card(DIAMOND, JACK));
        bebe.receiveCard(new Card(CLOVER, TWO));

        // when, then
        assertThat(participants.findPlayerFinalResult().get(bebe)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러가 21이하이고 플레이어는 21초과이면 플레이어의 패배가 반환된다.")
    void whenPlayerBurstReturnFalse() {
        // given
        dealer.receiveCard(new Card(SPADE, KING));

        bebe.receiveCard(new Card(SPADE, JACK));
        bebe.receiveCard(new Card(DIAMOND, NINE));
        bebe.receiveCard(new Card(CLOVER, THREE));

        // when, then
        assertThat(participants.findPlayerFinalResult().get(bebe)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러가 21초과이고 플레이어는 21이하이면 플레이어의 승리가 반환된다.")
    void whenDealerBurstReturnTrue() {
        // given
        dealer.receiveCard(new Card(SPADE, JACK));
        dealer.receiveCard(new Card(DIAMOND, NINE));
        dealer.receiveCard(new Card(CLOVER, THREE));

        bebe.receiveCard(new Card(SPADE, KING));

        // when, then
        assertThat(participants.findPlayerFinalResult().get(bebe)).isEqualTo(WIN);
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        participants.receiveInitialCards(Deck.create(new RandomShuffleMaker()));

        // when, then
        assertAll(
                () -> assertThat(bebe.getHand().getCards()).hasSize(2),
                () -> assertThat(dealer.getHand().getCards()).hasSize(2)
        );
    }

    @Nested
    @DisplayName("딜러의 최종 결과를 반환한다.")
    class DealerResultTest {

        @Test
        @DisplayName("딜러가 플레이어의 결과보다 크면 딜러의 결과로 승리가 반환된다.")
        void givenDealerMoreValueThanPlayer_whenFindDealerFinalResult_thenReturnWin() {
            // given
            dealer.receiveCard(new Card(DIAMOND, KING));
            bebe.receiveCard(new Card(DIAMOND, NINE));

            // when
            final Map<Result, Long> result = participants.findDealerFinalResult();

            // then
            assertThat(result.get(WIN)).isEqualTo(1L);
        }

        @Test
        @DisplayName("딜러가 플레이어의 결과와 같으면 딜러의 결과로 승리가 반환된다.")
        void givenDealerSameValueThanPlayer_whenFindDealerFinalResult_thenReturnWin() {
            // given
            dealer.receiveCard(new Card(DIAMOND, KING));
            bebe.receiveCard(new Card(DIAMOND, JACK));

            // when
            final Map<Result, Long> result = participants.findDealerFinalResult();

            // then
            assertThat(result.get(TIE)).isEqualTo(1L);
        }

        @Test
        @DisplayName("딜러가 플레이어의 결과보다 작으면 딜러의 결과로 승리가 반환된다.")
        void givenDealerLessValueThanPlayer_whenFindDealerFinalResult_thenReturnWin() {
            // given
            dealer.receiveCard(new Card(DIAMOND, NINE));
            bebe.receiveCard(new Card(DIAMOND, JACK));

            // when
            final Map<Result, Long> result = participants.findDealerFinalResult();

            // then
            assertThat(result.get(LOSE)).isEqualTo(1L);
        }
    }
}
