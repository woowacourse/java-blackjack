package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer("딜러");
    }

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Dealer("딜러"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 참가자에게 상속받았는지 확인")
    void extend() {
        final Player player = new Dealer("딜러");
        player.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 받았는지 확인")
    void receiveCard() {
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인")
    void checkMoreCardAvailable() {
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.checkMoreCardAvailable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 갖고 있는 카드의 합을 확인")
    void calculateMyCardSum() {
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.calculate()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        Assertions.assertThat(dealer.calculate()).isEqualTo(13);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateMyCardSumWhenAceIsTwo(final String input, final int expected) {
        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        Assertions.assertThat(dealer.calculate()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트인지 확인")
    void isBust() {
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.HEART));
        AssertionsForClassTypes.assertThat(dealer.isBust()).isTrue();
    }
}
