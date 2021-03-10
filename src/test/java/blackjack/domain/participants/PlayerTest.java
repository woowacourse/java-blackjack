package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    private Participant player;

    @BeforeEach
    void setUp() {
        player = new Player("bada");
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
            .doesNotThrowAnyException();

        assertThatCode(() -> new Player("bada", 1000.0))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자의 초기 돈을 확인")
    void checkInitialMoney() {
        final Participant player = new Player("j.on");
        assertThat(player.getMoney()).isEqualTo(new Money(0));
        assertThat(player.getMoney()).isEqualTo(new Money());
    }

    @Test
    @DisplayName("참가자의 현재 돈을 확인")
    void checkMoney() {
        final Participant player = new Player("j.on", 1000.0);
        assertThat(player.getMoney()).isEqualTo(new Money(1000.0));
    }

    @Test
    @DisplayName("참가자가 카드를 받았는지 확인")
    void receiveCard() {
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("참가자가 처음에 카드 두 장을 보여주는지 확인")
    void showCards() {
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(player.initialCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자가 갖고 있는 카드의 합을 확인")
    void calculateMyCardSum() {
        player.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(player.calculate()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        player.receiveCard(new Card(CardNumber.JACK, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.FIVE, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(player.calculate()).isEqualTo(16);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,KING:21", "ACE,ACE:12", "ACE,ACE,ACE:13",
        "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateMyCardSumWhenAceIsTwo(final String input, final int expected) {
        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            player.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(player.calculate()).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자가 버스트인지 확인")
    void isBust() {
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        player.receiveCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("참가자가 블랙잭인지 확인")
    void isBlackjack() {
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(player.isBlackjack()).isTrue();

        player.receiveCard(new Card(CardNumber.ACE, CardType.HEART));
        player.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        assertThat(player.isBlackjack()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("딜러가 블랙잭이 아닌 상황에서 참가자의 경기 결과 구하기")
    @CsvSource(value = {"KING,KING,KING:LOSE", "ACE:LOSE", "KING,NINE:DRAW", "ACE,TEN:BLACKJACK",
        "KING,KING:WIN"}, delimiter = ':')
    void checkPlayerGameResult(final String input, final Result result) {
        final Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardNumber.KING, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.NINE, CardType.CLOVER));

        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            player.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(player.decideWinner(dealer)).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("딜러가 블랙잭인 상황에서 참가자의 경기 결과 구하기")
    @CsvSource(value = {"ACE,TEN:DRAW", "ACE:LOSE"}, delimiter = ':')
    void checkPlayerGameResultWhenDealerBlackjack(final String input, final Result result) {
        final Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.KING, CardType.CLOVER));

        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            player.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(player.decideWinner(dealer)).isEqualTo(result);
    }
}
