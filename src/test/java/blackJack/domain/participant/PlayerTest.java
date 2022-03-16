package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;

class PlayerTest {

    @Test
    @DisplayName("Player 생성 테스트")
    void createValidPlayer() {
        assertThat(new Player("rookie")).isNotNull();
    }

    @ParameterizedTest(name = "플레이어의 이름 공백인 경우 검증 테스트")
    @ValueSource(strings = {" ", ""})
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'인 경우 검증 테스트")
    void checkProhibitName() {
        assertThatThrownBy(() -> new Player("딜러"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalsePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TWO));

        assertThat(player.isAvailableHit()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 테스트")
    void hasTruePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

        assertThat(player.isAvailableHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드 합계 계산 테스트")
    void calculateScore() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.JACK));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        Player player = new Player("rookie");
        player.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.HEART, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.DIAMOND, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외 발생 테스트")
    void receiveDuplicatedCard() {
        Card card1 = Card.valueOf(Suit.CLOVER, Denomination.ACE);
        Card card2 = Card.valueOf(Suit.CLOVER, Denomination.ACE);
        Participant participant = new Player("rookie");
        participant.receiveCard(card1);

        assertThatThrownBy(() -> participant.receiveCard(card2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 카드는 받을 수 없습니다.");
    }
}
