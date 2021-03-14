package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    @DisplayName("add 테스트")
    void testAdd() {
        //when
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));

        //then
        assertThat(cards.getCards().contains(Card.of(Suit.SPADE, Denomination.ACE)));
    }

    @Test
    @DisplayName("점수 총합 테스트")
    void testTotalScore() {
        //given
        cards.add(Card.of(Suit.SPADE, Denomination.FIVE));
        cards.add(Card.of(Suit.SPADE, Denomination.TEN));

        //when
        Score totalScore = cards.totalScore();

        //then
        assertThat(totalScore).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("에이스를 보유하는지 테스트")
    void testHasAce() {
        //given
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        cards.add(Card.of(Suit.SPADE, Denomination.TEN));

        //when
        boolean ace = cards.hasAce();

        //then
        assertThat(ace).isTrue();
    }


    @Test
    @DisplayName("카드가 두장뿐이고, 점수가 21일 때 블랙잭 테스트")
    void testBlackJack() {
        //given
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        cards.add(Card.of(Suit.SPADE, Denomination.JACK));

        //when
        boolean onlyTwoCard = cards.isOnlyTwoCard();
        Score totalScore = cards.totalScore();
        boolean blackJack = cards.isBlackJack();

        //then
        assertThat(onlyTwoCard).isTrue();
        assertThat(totalScore).isEqualTo(Score.of(21));
        assertThat(blackJack).isTrue();
    }
}
