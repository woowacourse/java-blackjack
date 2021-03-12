package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticiapantTest {
    private Participant dealer;
    private Participant player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("bada", 10000);
    }

    @Test
    @DisplayName("player 이름의 빈값을 잘 검증하는지 확인")
    void validateEmptyName() {
        assertThatThrownBy(() -> new Player("", 10000)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participant.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("초기카드로 2장씩을 잘 분배하는지 확인")
    void receiveInitialCards() {
        final CardDeck cardDeck = new CardDeck();
        dealer.receiveInitialCards(cardDeck);
        assertThat(dealer.cardCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 한 장 분배가 잘 되는지 확인")
    void receiveCard() {
        dealer.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러와 플레이어가 카드를 더 받을 수 있는지 확인")
    void checkMoreCardAvailable() {
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.checkMoreCardAvailable()).isTrue();

        player.receiveOneCard(new Card(CardNumber.JACK, CardType.HEART));
        player.receiveOneCard(new Card(CardNumber.TEN, CardType.HEART));
        assertThat(player.checkMoreCardAvailable()).isTrue();
    }

    @Test
    @DisplayName("카드의 합 계산이 잘 되는지 확인")
    void calculateCardSum() {
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateCardSumWhenAceIsOne() {
        dealer.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        assertThat(dealer.calculateScore()).isEqualTo(13);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateCardSumWhenAceIsTwo(final String input, final int expected) {
        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveOneCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 확인이 잘 되는지 확인")
    void isBust() {
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.NINE, CardType.HEART));
        dealer.receiveOneCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(dealer.isBust()).isTrue();
    }
}
