package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

    private Participant dealer;

    @BeforeEach
    void setUp() {
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

    @ParameterizedTest
    @CsvSource(value = {"ACE,KING:LOSE", "TWO,THREE:WIN"}, delimiter = ':')
    @DisplayName("딜러의 승패를 확인")
    void isWinner(final String input, final Result expected) {
        final String[] inputs = input.split(",");
        final List<CardNumber> cardNumbers = Arrays.stream(inputs)
            .map(CardNumber::valueOf)
            .collect(Collectors.toList());
        final Participant player = new Player("j.on");
        player.receiveCard(new Card(cardNumbers.get(0), CardType.CLOVER));
        player.receiveCard(new Card(cardNumbers.get(1), CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.HEART));
        assertThat(dealer.decideWinner(player).isSameResult(expected)).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트가 되는 경우 패배가 되는지 확인")
    void isWinnerWhenDealerIsBust() {
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.HEART));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.SPADE));
        assertThat(dealer.decideWinner(new Player("j.on")).isSameResult(Result.LOSE)).isTrue();
    }

    @Test
    @DisplayName("딜러의 결과가 무승부가 되는지 확인")
    void isWinner() {
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        final Participant player = new Player("j.on");
        player.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        assertThat(dealer.decideWinner(player)).isEqualTo(Result.DRAW);
    }
}
