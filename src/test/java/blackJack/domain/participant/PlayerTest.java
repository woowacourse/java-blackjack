package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import blackJack.domain.money.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;

class PlayerTest {

    @Test
    @DisplayName("Player가 성공적으로 생성된 경우 null 값을 반환한다.")
    void createValidPlayer() {
        assertThat(new Player("rookie")).isNotNull();
    }

    @ParameterizedTest(name = "플레이어의 이름에 공백이 있는 경우 예외를 발생시킨다.")
    @ValueSource(strings = {" ", ""})
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'인 경우 예외를 발생시킨다.")
    void checkProhibitName() {
        assertThatThrownBy(() -> new Player("딜러"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 불가능한 경우 False를 반환시킨다.")
    void hasFalsePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TWO));

        assertThat(player.isAvailableHit()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 True를 반환시킨다.")
    void hasTruePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

        assertThat(player.isAvailableHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드 합계 총 점수를 계산할 수 있다.")
    void calculateScore() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어의 카드 중 Ace를 소유하고, 카드 총합에 10 더했을때 21을 초과하지 않는 경우 ACE의 점수는 11로 계산한다.")
    void calculateScoreWithAceEleven() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.JACK));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 카드 중 Ace를 소유하고, 카드 총합에 10 더했을때 21을 초과하는 경우 ACE의 점수는 1로 계산한다.")
    void calculateScoreWithAceOne() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 여러개인 경우, 카드 총합에 10 더했을때 21을 초과하지 않는 경우 ACE의 점수는 11로 계산한다.")
    void calculateScoreWithAceCountThree() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.DIAMOND, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("참가자가 중복된 카드를 받는 경우 예외가 발생한다.")
    void receiveDuplicatedCard() {
        Card card1 = Card.valueOf(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.valueOf(Suit.CLOVER, Denomination.ACE);
        Participant participant = new Player("rookie");
        participant.receiveCard(card1);

        assertThatThrownBy(() -> participant.receiveCard(card2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 카드는 받을 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어는 베팅 금액을 추가할 수 있다.")
    void playerAddBetting() {
        Player player = new Player("rookie");
        player.betting(1000);

        assertThat(player.getBet()).isEqualTo(new Bet(1000));
    }
}
