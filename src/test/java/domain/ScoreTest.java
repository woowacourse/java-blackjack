package domain;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    private List<Card> playingCards = new ArrayList<>();

    @Test
    @DisplayName("Score 생성 테스트")
    void valueOf() {
        assertThat(Score.valueOf(0)).isNotNull();
    }

    @Test
    @DisplayName("음수 입력 시 Score 생성 예외 테스트")
    void valueOfException1() {
        assertThatThrownBy(() -> Score.valueOf(-1))
                .isInstanceOf(NegativeScoreException.class)
                .hasMessage("Score 값은 양수이어야 합니다.");
    }

    @Test
    @DisplayName("null값 입력 시 Score 생성 예외 테스트")
    void valueOfException2() {
        assertThatThrownBy(() -> Score.valueOf(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    @DisplayName("Cards의 Score를 계산")
    void calculateScore() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.FIVE, Type.DIAMOND));
        assertThat(Score.valueOf(playingCards)).isEqualTo(Score.valueOf(15));
    }

    @Test
    @DisplayName("버스트 여부에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceNotBurst() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.ACE, Type.DIAMOND));
        assertThat(Score.valueOf(playingCards)).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("버스트 여부에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceOnBurst() {
        playingCards.add(new Card(Symbol.TEN, Type.DIAMOND));
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThat(Score.valueOf(playingCards)).isEqualTo(Score.valueOf(20));
    }

    @Test
    @DisplayName("버스트 여부에 따라서 ACE값이 결정되서 계산한다")
    void calculateWithAceOnBurst1() {
        playingCards.add(new Card(Symbol.NINE, Type.CLOVER));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        playingCards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThat(Score.valueOf(playingCards)).isEqualTo(Score.valueOf(21));
    }

    @ParameterizedTest
    @DisplayName("버스트인지 확인")
    @CsvSource(value = {"1,false", "21,false", "22,true"})
    void isBust(int value, boolean expected) {
        assertThat(Score.valueOf(value).isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("버스트가 아닌지 확인")
    @CsvSource(value = {"1,true", "21,true", "22,false"})
    void isNotBust(int value, boolean expected) {
        assertThat(Score.valueOf(value).isNotBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("21점인지 확인")
    @CsvSource(value = {"1,false", "21,true", "22,false"})
    void isBlackJackScore(int value, boolean expected) {
        assertThat(Score.valueOf(value).isBlackJackScore()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("21점이 아닌지 확인")
    @CsvSource(value = {"1,true", "21,false", "22,true"})
    void isNotBlackJackScore(int value, boolean expected) {
        assertThat(Score.valueOf(value).isNotBlackJackScore()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("compareTo 오버라이드 메소드 확인")
    @CsvSource(value = {"1,2,-1", "11,10,1", "21,21,0"})
    void compareTo(int score, int comparedScore, int expected) {
        assertThat(Score.valueOf(score).compareTo(Score.valueOf(comparedScore))).isEqualTo(expected);
    }
}
