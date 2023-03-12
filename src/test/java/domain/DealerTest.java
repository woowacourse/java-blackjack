package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Test
    void 점수가_16이하면_카드를_뽑는다() {
        //given,when
        new Deck();
        Dealer dealer = new Dealer(new Cards(new ArrayList<>()));
        int expected = 1;
        int actual = dealer.getCardsSize();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void 점수가_17이상이면_카드를_뽑지_않는다() {
        //given
        Dealer dealer = new Dealer(
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.CLOVER, Denomination.SEVEN))
                ));
        //when
        int expected = 2;
        int actual = dealer.getCardsSize();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void 정상_생성_테스트() {
        //given, when, then
        assertDoesNotThrow(()-> new Dealer(
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.CLOVER, Denomination.SEVEN))
                )));
    }
}
