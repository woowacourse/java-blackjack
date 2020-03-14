package blackjack.player.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.card.domain.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private static Stream<Arguments> playerProvider() {
        return Stream.of(
                Arguments.of(
                        new Dealer(CardBundle.emptyBundle()), false, true
                ),
                Arguments.of(
                        new Gambler(CardBundle.emptyBundle(), "pobi"), true, false
                )
        );
    }

    private static Stream<Arguments> cardBundleProvider() {
        return Stream.of(
                Arguments.of(
                        aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.TWO), true
                ),
                Arguments.of(
                        aCardBundle(CardNumber.KING, CardNumber.ACE), false
                )
        );
    }

    private static Stream<Arguments> createArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, "bebop", "CardBundle이 비었습니다."),
                Arguments.of(CardBundle.emptyBundle(), null, "이름이 비었습니다.")
        );
    }

    @DisplayName("플레이어의 패가 블랙잭인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,false", "KING,true"})
    void isBlackjack(CardNumber cardNumber, boolean result) {
        //given
        Player player = new Dealer(CardBundle.emptyBundle());
        Symbol club = Symbol.CLUB;
        player.addCard(Card.of(club, CardNumber.ACE));
        player.addCard(Card.of(club, cardNumber));

        //when
        boolean isBlackjack = player.isBlackjack();
        //then
        assertThat(isBlackjack).isEqualTo(result);
    }

    @DisplayName("플레이어의 패가 21을 초과했는지 확인")
    @ParameterizedTest
    @MethodSource("cardBundleProvider")
    void isBurst(CardBundle cardBundle, boolean result) {
        //given
        Player player = new Dealer(cardBundle);
        //when
        boolean isBurst = player.isBurst();
        //than
        assertThat(isBurst).isEqualTo(result);
    }

    @DisplayName("플레이어의 패가 21이하인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false"})
    void isNotBurst(CardNumber cardNumber, boolean result) {
        //given
        Player player = new Dealer(CardBundle.emptyBundle());
        player.addCard(Card.of(Symbol.HEART, CardNumber.TEN));
        player.addCard(Card.of(Symbol.HEART, CardNumber.TEN));
        player.addCard(Card.of(Symbol.HEART, cardNumber));
        //when
        boolean notBurst = player.isNotBurst();
        //then
        assertThat(notBurst).isEqualTo(result);
    }

    @DisplayName("생성시 카드번들 혹은 이름이 없다면 Exception 발생")
    @ParameterizedTest
    @MethodSource("createArgumentsProvider")
    void create(CardBundle cardBundle, String name, String messgae) {
        assertThatThrownBy(() -> new Gambler(cardBundle, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(messgae);
    }
}