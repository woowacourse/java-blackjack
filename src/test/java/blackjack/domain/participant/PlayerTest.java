package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bada", 10000);
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada", 10000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 Participant에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Player("bada", 10000);
        participant.receiveOneCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("금액이 음수일 때 익셉션을 잘 날리는지 확인")
    void validateMoney() {
        final int falseMoney = -10;
        assertThatThrownBy(() -> new Player("bada", falseMoney)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.INVALID_MONEY_ERROR);
    }

    @Test
    @DisplayName("플레이어의 초기 카드 출력이 두 장 다 되는지 확인")
    void showInitialCards() {
        final Card firstCard = new Card(CardNumber.JACK, CardType.DIAMOND);
        final Card secondCard = new Card(CardNumber.EIGHT, CardType.CLOVER);
        player.receiveInitialCards(firstCard, secondCard);
        assertThat(player.getInitialCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,JACK:LOSE", "FIVE,JACK,NINE:LOSE", "NINE,FIVE,THREE:LOSE"}, delimiter = ':')
    @DisplayName("플레이어가 버스트일 때 결과 확인")
    void findResultWhenBust(final String input, final String expected) {
        player.receiveOneCard(new Card(CardNumber.KING, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.JACK, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.NINE, CardType.CLOVER));
        final String[] inputs = input.split(",");
        final Result expectedResult = Result.valueOf(expected);
        final Dealer dealer = new Dealer();
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveOneCard(new Card(cardNumber, CardType.HEART));
        }
        assertThat(player.findResult(dealer)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,JACK:DRAW", "FIVE,JACK,NINE:BLACKJACK", "NINE,FIVE,THREE:BLACKJACK"}, delimiter = ':')
    @DisplayName("플레이어가 블랙잭일 때 결과 확인")
    void findResultWhenBlackjack(final String input, final String expected) {
        player.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.JACK, CardType.CLOVER));
        final String[] inputs = input.split(",");
        final Result expectedResult = Result.valueOf(expected);
        final Dealer dealer = new Dealer();
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveOneCard(new Card(cardNumber, CardType.HEART));
        }
        assertThat(player.findResult(dealer)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,JACK:LOSE", "FIVE,JACK,NINE:WIN", "NINE,FIVE,THREE:DRAW"}, delimiter = ':')
    @DisplayName("플레이어가 버스트도 블랙잭도 아닐 때 결과 확인")
    void findResultOtherCase(final String input, final String expected) {
        player.receiveOneCard(new Card(CardNumber.SEVEN, CardType.CLOVER));
        player.receiveOneCard(new Card(CardNumber.JACK, CardType.CLOVER));
        final String[] inputs = input.split(",");
        final Result expectedResult = Result.valueOf(expected);
        final Dealer dealer = new Dealer();
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveOneCard(new Card(cardNumber, CardType.HEART));
        }
        assertThat(player.findResult(dealer)).isEqualTo(expectedResult);
    }
}
