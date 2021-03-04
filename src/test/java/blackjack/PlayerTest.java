package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participants.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bada");
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자가 카드를 받았는지 확인")
    void receiveCard() {
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("참가자가 갖고 있는 카드의 합을 확인")
    void calculateMyCardSum() {
        player.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        Assertions.assertThat(player.calculate()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        player.receiveCard(new Card(CardNumber.JACK, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.FIVE, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        Assertions.assertThat(player.calculate()).isEqualTo(16);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateMyCardSumWhenAceIsTwo(final String input, final int expected) {
        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            player.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        Assertions.assertThat(player.calculate()).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자가 버스트인지 확인")
    void isBust() {
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        player.receiveCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(player.isBust()).isTrue();
    }
}
