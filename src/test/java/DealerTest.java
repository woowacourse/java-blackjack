import card.CardFactory;
import card.Symbol;
import card.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {
    @Test
    void 이름_반환_확인_테스트() {
        Dealer dealer = new Dealer();

        Assertions.assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 딜러의_상태_반환_확인_테스트() {
        Dealer dealer = new Dealer();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        dealer.draw(CardFactory.of(type, symbol));
        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("SEVEN");
        dealer.draw(CardFactory.of(type, symbol));
        Assertions.assertThat(dealer.getFirstStatus()).isEqualTo("딜러: A스페이드");
    }
}
