package domain.user;

import domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
    @Test
    void 이름_반환_확인_테스트() {
        Dealer dealer = new Dealer();

        Assertions.assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @ParameterizedTest
    @CsvSource(value = {"SIX,TEN,true","ACE,TWO,true","KING,QUEEN,false"})
    void 카드_추가_드로우_로직_테스트(String first, String second, boolean expected){
        Dealer dealer = new Dealer();
        List<Card> testDeck = new ArrayList<>();
        testDeck.add(CardFactory.of(Type.valueOf("SPADE"), Symbol.valueOf(first)));
        testDeck.add(CardFactory.of(Type.valueOf("SPADE"), Symbol.valueOf(second)));
        CardDeck cardDeck = CardDeckGeneratorForTest.create(testDeck);
        dealer.draw(cardDeck);
        dealer.draw(cardDeck);

        Assertions.assertThat(dealer.isDrawable()).isEqualTo(expected);
    }
}
