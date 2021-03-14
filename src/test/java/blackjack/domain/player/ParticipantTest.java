package blackjack.domain.player;

import static blackjack.domain.card.CardSpec.ACE;
import static blackjack.domain.card.CardSpec.KING;
import static blackjack.domain.card.CardSpec.QUEEN;
import static blackjack.domain.card.CardSpec.TWO;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.state.Hit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("첫 카드 두 장의 합이 21 일 경우 블랙잭인 지 확인")
    void blackjack() {
        Participant participant = Participant.of("nabom", 1000, ACE.card(), KING.card());
        Assertions.assertThat(participant.isBlackJack()).isTrue();

    }

    @Test
    @DisplayName("첫 카드 두 장의 합이 21이 아닐 경우")
    void hit() {
        Participant participant = Participant.of("nabom", 1000, TWO.card(), KING.card());
        Assertions.assertThat(participant.state()).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 넘길 때 스코어도 같이 잘 넘어가는 지 확인")
    void drawCard() {
        Participant participant = Participant.of("nabom", 1000, TWO.card(), KING.card());
        participant.drawCard(Card.of(CardNumber.THREE, Symbol.CLOVER));
        Assertions.assertThat(participant.score()).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("카드를 넘길 때 21 스코어가 넘어갈 경우 버스트 상태로 변환되는 지 확인")
    void drawCard_bust() {
        Participant participant = Participant.of("nabom", 1000, TWO.card(), KING.card());
        participant.drawCard(QUEEN.card());
        Assertions.assertThat(participant.score()).isEqualTo(Score.of(22));
        Assertions.assertThat(participant.isBust()).isTrue();
    }

    @Test
    @DisplayName("블랙잭인 경우 1.5배의 위닝 머니를 얻는 지 확인")
    void winningMoney_blackjack() {
        int batMoney = 10000;
        int expectedWinningMoney = 15000;
        Participant participant = Participant.of("nabom", batMoney, ACE.card(), KING.card());
        Assertions.assertThat(participant.winningMoney()).isEqualTo(expectedWinningMoney);
    }

    @Test
    @DisplayName("히트일 경우 1배의 위닝 머니를 얻는 지 확인")
    void winningMoney_hit() {
        int batMoney = 10000;
        int expectedWinningMoney = 10000;
        Participant participant = Participant.of("nabom", batMoney, TWO.card(), KING.card());
        Assertions.assertThat(participant.winningMoney()).isEqualTo(expectedWinningMoney);
    }

    @Test
    @DisplayName("버스트일 경우 -1배의 위닝 머니를 얻는 지 확인")
    void winningMoney_bust() {
        int batMoney = 10000;
        int expectedWinningMoney = -10000;
        Participant participant = Participant.of("nabom", batMoney, TWO.card(), KING.card());
        participant.drawCard(QUEEN.card());
        Assertions.assertThat(participant.winningMoney()).isEqualTo(expectedWinningMoney);
    }
}