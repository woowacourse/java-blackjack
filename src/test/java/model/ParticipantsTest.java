package model;

import model.card.Card;
import model.card.Deck;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.SPADE;
import static model.card.Value.ACE;
import static model.card.Value.FIVE;
import static model.card.Value.FOUR;
import static model.card.Value.JACK;
import static model.card.Value.KING;
import static model.card.Value.NINE;
import static model.card.Value.THREE;
import static model.card.Value.TWO;
import static model.user.Score.LOSE;
import static model.user.Score.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ParticipantsTest {

    private Participants participants;
    private Dealer dealer;
    private Player bebe;

    @BeforeEach
    void init() {
        participants = Participants.from(List.of(Player.from("bebe", 1000)));
        dealer = participants.getDealer();
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
                () -> assertThat(participants.getPlayersFinalResult()).containsExactly(LOSE),
                () -> {
                    bebe.receiveCard(new Card(CLOVER, TWO));
                    assertThat(participants.getPlayersFinalResult()).containsExactly(WIN);
                });
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 21 초과일 경우 플레이어가 패배한 결과가 반환된다.")
    void whenOverBurstNumberFindWinPlayer() {
        // given
        dealer.receiveCard(new Card(SPADE, KING));
        dealer.receiveCard(new Card(DIAMOND, KING));
        dealer.receiveCard(new Card(CLOVER, THREE));

        bebe.receiveCard(new Card(SPADE, JACK));
        bebe.receiveCard(new Card(DIAMOND, JACK));
        bebe.receiveCard(new Card(CLOVER, TWO));

        // when, then
        assertThat(participants.getPlayersFinalResult()).containsExactly(LOSE);
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
        assertThat(participants.getPlayersFinalResult()).containsExactly(LOSE);
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
        assertThat(participants.getPlayersFinalResult()).containsExactly(WIN);
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        participants.receiveInitialCards(new Deck());

        // when, then
        assertAll(
                () -> assertThat(bebe.getHand().getCards()).hasSize(2),
                () -> assertThat(dealer.getHand().getCards()).hasSize(2)
        );
    }

    @DisplayName("딜러가 게임 승리시 수익을 반환한다.")
    @Test
    void getDealerWinProfit() {
        // given
        dealer.receiveCard(new Card(SPADE, FIVE));
        bebe.receiveCard(new Card(SPADE, FOUR));

        // when, then
        Assertions.assertThat(participants.getDealerProfit()).isEqualTo(1000);
    }

    @DisplayName("딜러가 게임 패배시 수익 반환한다.")
    @Test
    void getDealerLoseProfit() {
        // given
        dealer.receiveCard(new Card(SPADE, THREE));
        bebe.receiveCard(new Card(SPADE, ACE));

        // when, then
        Assertions.assertThat(participants.getDealerProfit()).isEqualTo(-1000);
    }

    @DisplayName("플레이어가 블랙잭일 때는 딜러에게 베팅 금액의 1.5배를 받는다.")
    @Test
    void getPlayerIsBlackJackProfit() {
        // given
        dealer.receiveCard(new Card(CLOVER, KING));
        bebe.receiveCard(new Card(DIAMOND, ACE));
        bebe.receiveCard(new Card(DIAMOND, JACK));

        // when
        participants.ifPlayerIsBlackJackReceiveMoney();

        // then
        Assertions.assertThat(bebe.getMoney()).isEqualTo(1500);
    }

    @DisplayName("플레이어가 처음 받은 패에서 블랙잭이면 딜러에게 배팅 금액의 1.5배를 받는다.")
    @Test
    void ifPlayerBlackJackReceiveMoney() {
        // given
        bebe.receiveCard(new Card(CLOVER, ACE));
        bebe.receiveCard(new Card(CLOVER, JACK));

        // when
        participants.ifPlayerIsBlackJackReceiveMoney();

        // then
        Assertions.assertThat(bebe.getMoney()).isEqualTo(1500);

    }
}
