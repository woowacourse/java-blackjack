package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import exception.NameFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.InputUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("잘못된 이름 입력시 예외처리")
    void isValidNameTest() {
        assertThatThrownBy(() -> new Player("po/bi", "50")).isInstanceOf(NameFormatException.class);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("플레이어가 추가로 카드를 뽑을 수 있을지 테스트")
    public void isDrawableTest(List<Card> cards, boolean expected) {
        Player player = new Player("pobi", "50");
        player.addCard(cards);
        assertThat(player.isDrawable()).isEqualTo(expected);
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.SIX),
                        new Card(CardSuit.CLOVER, CardNumber.TEN)), true),
                Arguments.of(Arrays.asList(
                        new Card(CardSuit.CLOVER, CardNumber.KING),
                        new Card(CardSuit.CLOVER, CardNumber.TEN),
                        new Card(CardSuit.CLOVER, CardNumber.THREE)), false));
    }
}