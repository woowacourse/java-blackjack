package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @Test
    void 딜러_생성_성공() {
        // given nothing

        // when & then
        assertThatCode(() -> Dealer.of(CardDeck.of()))
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_받는다() {
        // given
        Dealer dealer = Dealer.of(CardDeck.of());

        // when
        dealer.receive(dealer.drawCard());

        // then
        assertThat(dealer.getOwnedCards()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("dealerAndHitResult")
    void 딜러가_카드를_더_받아야하는지_판단한다(Dealer dealer, boolean expectedResult) {
        // when
        boolean result = dealer.canHit();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> dealerAndHitResult() {
        Dealer hitDealer = Dealer.of(CardDeck.of());
        hitDealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        hitDealer.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));

        Dealer notHitDealer = Dealer.of(CardDeck.of());
        notHitDealer.receive(Card.of(TrumpNumber.THREE, TrumpShape.CLUB));
        notHitDealer.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        return Stream.of(
                Arguments.of(hitDealer, false),
                Arguments.of(notHitDealer, true)
        );
    }
}
