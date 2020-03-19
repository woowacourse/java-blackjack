package blackjack.view.console;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StringParserTest {

    @Test
    @DisplayName("comma로 구분한 list 생성")
    void namesSpliter() {
        String source = "a,b,c";
        assertThat(StringParser.splitWithComma(source)).size().isEqualTo(3);
    }

    @Test
    @DisplayName("hand를 받아 String으로 변환")
    void handString() {
        List<Card> hand = Arrays.asList(new Card(CardSymbol.ACE, CardType.SPADE), new Card(CardSymbol.KING, CardType.CLOVER));
        String result = StringParser.parseHandToString(hand);
        assertThat(result).isEqualTo("A스페이드,K클로버");
    }
}