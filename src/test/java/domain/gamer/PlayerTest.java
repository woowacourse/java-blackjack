package domain.gamer;

import domain.TestDeck;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.card.Deck;
import domain.money.Money;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Player dealer = new Player(new Name("test"));
        List<Card> cards = dealer.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("플레이어는 설정한 개수만큼 카드를 받을 수 있다.")
    void pickCards() {
        Player player = new Player(new Name("test"));
        Card one = new Card(CardType.CLOVER, CardNumber.ACE);
        Card two = new Card(CardType.CLOVER, CardNumber.TWO);
        Deck deck = TestDeck.withCustomCards(one, two);
        player.pickCards(deck, 2);
        List<Card> cards = player.getCards();
        Assertions.assertThat(cards)
                .contains(one)
                .contains(two);
    }

    @Test
    @DisplayName("플레이어는 카드를 한 번만 뽑을 수 있다.")
    void takeCard() {
        Player player = new Player(new Name("test"));
        Card card = new Card(CardType.SPADE, CardNumber.TEN);
        Deck deck = TestDeck.withCustomCards(card);
        player.hit(deck);
        List<Card> cards = player.getCards();
        Assertions.assertThat(cards).isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("이름 객체로 플레이어의 이름을 검증할 수 있다.")
    void createName() {
        Player player = new Player(new Name("test"));
        Assertions.assertThat(player.hasName(new Name("test"))).isTrue();
    }

    @Test
    @DisplayName("게이머의 점수 합계를 반환한다.")
    void getTotalScore() {
        Player player = new Player(new Name("test"));
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.SPADE, CardNumber.TEN));
        player.hit(deck);
        Assertions.assertThat(player.getTotalScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("Bust이면 true를 반환한다.")
    void isBustTrue() {
        Player player = new Player(new Name("test"));
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN));

        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        Assertions.assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("Bust가 아니면 false를 반환한다.")
    void isBustFalse() {
        Player player = new Player(new Name("test"));
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN));
        player.pickCards(deck, 2);
        Assertions.assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("Player가 배팅할 때 승리일 경우 걸었던 돈 그대로 받는다.")
    void betWinNormalCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.SPADE, CardNumber.THREE),
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.DIAMOND, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(100));
    }

    @Test
    @DisplayName("Player가 배팅할 때 패배일 경우 걸었던 돈이 마이너스로 변환다.")
    void betLoseNormalCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.DIAMOND, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TWO),
                new Card(CardType.SPADE, CardNumber.THREE)
        );
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(-100));
    }

    @Test
    @DisplayName("Player가 배팅할 때 승리이고 블랙잭 일 경우 걸었던 돈의 1.5배를 반환한다.")
    void betWinBlackJackCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.DIAMOND, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.ACE),
                new Card(CardType.SPADE, CardNumber.KING)
        );
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(150));
    }

    @Test
    @DisplayName("Player가 배팅할 때 패배이고 딜러가 블랙잭일 경우 걸었던 돈이 마이너스로 변한다.")
    void betLoseBlackJackCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.DIAMOND, CardNumber.ACE),
                new Card(CardType.DIAMOND, CardNumber.KING),
                new Card(CardType.SPADE, CardNumber.KING),
                new Card(CardType.SPADE, CardNumber.TEN)
        );
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(-100));
    }

    @Test
    @DisplayName("Player가 배팅할 때 둘 다 블랙잭일 경우 걸었던 걸었던 돈 그대로 반환한다.")
    void betBothBlackJackCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.DIAMOND, CardNumber.ACE),
                new Card(CardType.DIAMOND, CardNumber.KING),
                new Card(CardType.SPADE, CardNumber.ACE),
                new Card(CardType.SPADE, CardNumber.KING)
        );
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(100));
    }

    @Test
    @DisplayName("Player가 배팅할 때 점수가 동일 할 경우 걸었던 걸었던 돈 그대로 반환한다.")
    void betSameScoreCase() {
        Player player = new Player(new Name("test"), new Money(100));
        Dealer dealer = new Dealer();
        Deck deck = TestDeck.withCustomCards(
                new Card(CardType.DIAMOND, CardNumber.KING),
                new Card(CardType.DIAMOND, CardNumber.JACK),
                new Card(CardType.SPADE, CardNumber.KING),
                new Card(CardType.SPADE, CardNumber.JACK)
        );
        player.pickCards(deck, 2);
        dealer.pickCards(deck, 2);
        Assertions.assertThat(player.bet(dealer)).isEqualTo(new Money(100));
    }
}
