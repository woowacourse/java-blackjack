package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

    @DisplayName("카드가 ACE 인지 아닌지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false"})
    void isAce(CardNumber cardNumber, boolean result) {
        Card card = Card.of(Symbol.HEART, cardNumber);
        assertThat(card.isAce()).isEqualTo(result);
    }

    @DisplayName("카드의 점수를 반환하는 기능")
    @ParameterizedTest
    @CsvSource(value = {"ACE,1", "TEN,10", "KING,10"})
    void name(CardNumber cardNumber, int result) {
        Card card = Card.of(Symbol.CLUB, cardNumber);
        assertThat(card.getNumber()).isEqualTo(result);
    }

    @DisplayName("싱글톤으로 만들어둔 CardCache에서 카드 가져오기")
    @Test
    void of() {
        //given
        Symbol symbol = Symbol.DIAMOND;
        CardNumber cardNumber = CardNumber.FIVE;

        //when
        Card card = Card.of(symbol, cardNumber);

        //then
        assertThat(card.getSymbol()).isEqualTo(Symbol.DIAMOND.getName());
        assertThat(card.getNumber()).isEqualTo(CardNumber.FIVE.getNumber());
    }

    @DisplayName("카드를 찾는 심볼과 번호가 null인 경우 Exception 발생")
    @ParameterizedTest
    @CsvSource(value = {",ACE,Symbol이 비어있습니다.", "HEART,,CardNumber가 비어있습니다."})
    void ofException(Symbol symbol, CardNumber cardNumber, String message) {
        assertThatThrownBy(() -> Card.of(symbol, cardNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @DisplayName("카드 이름 가져오기")
    @Test
    void getMessage() {
        //given
        Card card = Card.of(Symbol.DIAMOND, CardNumber.ACE);

        //when
        String message = card.getMessage();

        //then
        assertThat(message).isEqualTo("A");
    }

    @DisplayName("카드캐시의 private 생성자는 부를 수 없다.")
    @Test
    public void cacheCantCallPrivateConstruct() {
        List<Class<?>> nestedClasses = ReflectionUtils.findNestedClasses(Card.class, aClass -> true);

        Class<?> CardCache = nestedClasses.get(0);
        Constructor<?>[] declaredConstructors = CardCache.getDeclaredConstructors();
        Constructor<?> privateConstructor = declaredConstructors[0];
        privateConstructor.setAccessible(true);

        assertThatThrownBy(privateConstructor::newInstance)
                .hasCauseInstanceOf(UnsupportedOperationException.class);
    }
}