package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    private Participant player;

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.TEN), true),
                Arguments.of(new Card(Suit.HEART, Rank.NINE), false)
        );
    }

    @BeforeEach
    void setUp() {
        player = Player.create("Leo");
        player.receiveCard(new Card(Suit.HEART, Rank.TWO));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.KING));
    }

    @Test
    void receiveCard() {
        player.receiveCard(new Card(Suit.CLOVER, Rank.TEN));
        assertThat(player.getCardNames().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("cardProvider")
    @DisplayName("현재 참가자 패의 합이 21초과 여부에 따라 boolean을 반환한다.")
    void isBustTest(Card card, boolean isBust) {
        player.receiveCard(card);
        assertThat(player.isBust()).isEqualTo(isBust);
    }

    @Test
    void getHandValue() {
        assertThat(player.getHandValue()).isEqualTo(12);
    }

    @ParameterizedTest(name = "플레이어의 이름은 null이거나 공백일 수 없다.")
    @NullAndEmptySource
    void validatePlayerNameTest(String name) {
        assertThatThrownBy(() -> Player.create(name)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름은 '딜러'일 수 없다.")
    void validateNoDealer() {
        assertThatThrownBy(() -> Player.create("딜러")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름에 쉼표(,)가 포함 될 수 없다.")
    void validateNoComma() {
        assertThatThrownBy(() -> Player.create("이,름")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름의 길이가 10이상이 될 수 없다.")
    void validateNameLength() {
        assertThatThrownBy(() -> Player.create("이건 열 글자 넘는.")).isInstanceOf(IllegalArgumentException.class);
    }
}
