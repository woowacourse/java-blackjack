package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

    private Participant player;
    private Participant dealer;

    @BeforeEach
    void setUp() {
        player = new Player("j.on");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(Dealer::new)
            .doesNotThrowAnyException();

        assertThatCode(() -> new Dealer("딜러", 1000.0))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 초기 돈을 확인")
    void checkInitialMoney() {
        final Participant dealer = new Dealer();
        assertThat(dealer.getMoney()).isEqualTo(new Money(0));
        assertThat(dealer.getMoney()).isEqualTo(new Money());
    }

    @Test
    @DisplayName("딜러의 현재 돈을 확인")
    void checkMoney() {
        final Participant dealer = new Dealer(1000.0);
        assertThat(dealer.getMoney()).isEqualTo(new Money(1000.0));
    }

    @Test
    @DisplayName("딜러가 참가자에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Dealer();
        participant.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 받았는지 확인")
    void receiveCard() {
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 처음에 카드를 한 장 보여주는지 확인")
    void showCards() {
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.initialCards()).hasSize(1);
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
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러가 블랙잭인지 확인")
    void isBlackjack() {
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(dealer.isBlackjack()).isTrue();

        dealer.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        dealer.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("플레이어가 블랙잭이 아닌 상황에서 딜러의 경기 결과 구하기")
    @CsvSource(value = {"KING,KING,KING:LOSE", "ACE:LOSE", "KING,NINE:DRAW", "ACE,TEN:BLACKJACK",
        "KING,KING:WIN"}, delimiter = ':')
    void checkPlayerGameResult(final String input, final Result result) {
        player.receiveCard(new Card(CardNumber.KING, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.NINE, CardType.CLOVER));

        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(dealer.decideWinner(player)).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("딜러가 블랙잭인 상황에서 참가자의 경기 결과 구하기")
    @CsvSource(value = {"ACE,TEN:DRAW", "ACE:LOSE"}, delimiter = ':')
    void checkPlayerGameResultWhenDealerBlackjack(final String input, final Result result) {
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.KING, CardType.CLOVER));

        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(dealer.decideWinner(player)).isEqualTo(result);
    }

}
