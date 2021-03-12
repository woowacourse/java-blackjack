package blakcjack.domain.card;

import blakcjack.domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
    private Card clubAce;
    private Card clubTwo;
    private Card clubTen;
    private Card spadeTen;
    private Card spadeAce;

    @BeforeEach
    void setUp() {
        clubAce = Card.of(CardSymbol.CLUB, CardNumber.ACE);
        clubTwo = Card.of(CardSymbol.CLUB, CardNumber.TWO);
        clubTen = Card.of(CardSymbol.CLUB, CardNumber.TEN);
        spadeAce = Card.of(CardSymbol.SPADE, CardNumber.ACE);
        spadeTen = Card.of(CardSymbol.SPADE, CardNumber.TEN);
    }

    @DisplayName("카드 추가")
    @Test
    void add() {
        final Cards cards = new Cards(Arrays.asList(clubAce, clubTwo));
        cards.add(clubTen);
        assertThat(cards.toList()).containsExactly(clubAce, clubTwo, clubTen);
    }

    @DisplayName("Ace가 없는 경우 점수 계산")
    @Test
    void score_not_include_ace() {
        assertThat(new Cards(Arrays.asList(clubTwo, clubTen))
                .calculateScore())
                .isEqualTo(Score.from(12));

        assertThat(new Cards(Arrays.asList(clubTwo, clubTen, spadeTen))
                .calculateScore())
                .isEqualTo(Score.from(22));
    }

    @DisplayName("Ace가 있는 경우 점수 계산")
    @Test
    void score_include_ace() {
        assertThat(new Cards(Arrays.asList(clubAce, spadeAce, clubTen))
                .calculateScore())
                .isEqualTo(Score.from(12));

        assertThat(new Cards(Arrays.asList(clubAce, spadeAce, clubTwo))
                .calculateScore())
                .isEqualTo(Score.from(14));
    }
}
