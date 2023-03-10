package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("이름, 카드, 배팅액을 입력하면 플레이어 객체가 정상적으로 생성된다")
    void generatePlayer() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);

        assertDoesNotThrow(() -> new Player(name, cards, money));
    }

    @Test
    @DisplayName("보유하고 있는 초기 카드 2장의 합이 21일 경우 true를 리턴한다")
    void IsBlackjack() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("보유하고 있는 초기 카드 2장의 합이 21이 아닐 경우 false를 리턴한다")
    void IsNotBlackjack() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21미만이면 true를 리턴한다")
    void canReceiveOneMoreCard() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TWO)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.canReceiveOneMoreCard()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이상이면 true를 리턴한다")
    void canNotReceiveOneMoreCard() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TEN)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);

        assertThat(player.canReceiveOneMoreCard()).isFalse();
    }

    @Test
    @DisplayName("기존의 카드 그룹에 새로운 카드를 추가하면 카드의 개수가 1만큼 증가한다")
    void pickCard() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.TEN))));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        CardDeck cardDeck = CardDeck.generateCardDeck();
        int expectedCardSize = 3;

        player.pickCard(cardDeck);

        assertThat(player.getCards().size()).isEqualTo(expectedCardSize);
    }

    //
//    @Test
//    @DisplayName("player 이름이 5자를 초과하면 예외가 발생한다 ")
//    void nameValidateTest() {
//
//        String name = "abcdef";
//        Cards cards = new Cards(
//            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.CLOVER, Number.SIX)));
//
//        Assertions.assertThatThrownBy(() -> new Player(name, cards))
//            .isInstanceOf(IllegalArgumentException.class)
//            .hasMessage("이름은 5자 이하여야 합니다.");
//    }
//
//    @Test
//    @DisplayName("player의 카드 점수가 21 미만이면 계속 카드를 받을 수 있다.")
//    void canContinueTrue() {
//        //given
//        String name = "hoy";
//        Cards cards = new Cards(
//            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.CLOVER, Number.SIX)));
//        //when
//        Player player = new Player(name, cards);
//        //then
//        assertThat(player.canAddCard()).isTrue();
//    }
//
//    @Test
//    @DisplayName("player의 카드 점수가 21 이상이면 계속 카드를 받을 수 없다.")
//    void canContinueFalse() {
//        //given
//        String name = "hoy";
//        Cards cards = new Cards(
//            List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.CLOVER, Number.A)));
//        //when
//        Player player = new Player(name, cards);
//        //then
//        assertThat(player.canAddCard()).isFalse();
//    }
//
//    @Test
//    @DisplayName("딜러가 카드를 뽑으면 카드의 개수가 한장 추가된다")
//    void pickCard() {
//        //given
//        CardDeck cardDeck = new CardDeck();
//        Cards cards = new Cards(new ArrayList<>(List.of(new Card(Suit.CLOVER, Number.A), new Card(Suit.DIAMOND, Number.K))));
//        Player player = new Player("roy", cards);
//        int cardSize = player.getCards().getSize();
//
//        //when
//        Card card = player.pickCard(cardDeck);
//        player.hit(card);
//
//        //then
//        assertThat(player.getCards().getSize()).isEqualTo(cardSize + 1);
//    }
}
