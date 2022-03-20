package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.game.PlayRecord;

public class FinishedTest {

    @Test
    @DisplayName("draw를 호출하면 예외를 던진다.")
    void draw_error() {
        //given
        Finished finished = new Stay(new Cards(Set.of()));

        //then
        Assertions.assertThatThrownBy(() ->
                finished.draw(new Card(CardSymbol.DIAMOND, CardNumber.JACK))
            ).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Finished");
    }

    @Test
    @DisplayName("stay를 호출하면 예외를 던진다.")
    void stay_error() {
        //given
        Finished finished = new Blackjack(new Cards(Set.of()));

        //then
        Assertions.assertThatThrownBy(finished::stay
            ).isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Finished");
    }

    @Test
    @DisplayName("isDrawable의 반환값은 false이다.")
    void isDrawable_false() {
        //given
        Finished finished = new Bust(new Cards(Set.of()));

        //when
        boolean actual = finished.isDrawable();

        //then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"LOSS", "WIN", "BLACKJACK", "PUSH"})
    @DisplayName("Bust 수익률 검증 테스트")
    void bust_revenue(PlayRecord playRecord) {
        //given
        Finished finished = new Bust(new Cards(Set.of()));

        //when
        long actual = finished.revenue(playRecord, new Bet(100));

        //then
        assertThat(actual).isEqualTo(-100);
    }

    @ParameterizedTest
    @CsvSource(value = {"LOSS:-100", "WIN:100", "PUSH:0"}, delimiter = ':')
    @DisplayName("Stay 수익률 검증 테스트")
    void stay_revenue(PlayRecord playRecord, long expected) {
        //given
        Finished finished = new Stay(new Cards(Set.of()));

        //when
        long actual = finished.revenue(playRecord, new Bet(100));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK:150", "PUSH:0"}, delimiter = ':')
    @DisplayName("Blackjack 수익률 검증 테스트")
    void blackjack_revenue(PlayRecord playRecord, long expected) {
        //given
        Finished finished = new Blackjack(new Cards(Set.of()));

        //when
        long actual = finished.revenue(playRecord, new Bet(100));

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
