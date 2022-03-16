package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 패 생성자 테스트")
    @Test
    void constructor_CreateCards_HasInstance() {
        List<Card> cardHand = List.of(new Card(Number.ACE, Kind.HEART), new Card(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);

        assertThat(cards).isNotNull();
    }

    @DisplayName("중복 카드 패 생성자 테스트")
    @Test
    void constructor_HasDuplicateCards_ThrowsIllegalArgumentException() {
        List<Card> cardHand = List.of(new Card(Number.ACE, Kind.HEART), new Card(Number.ACE, Kind.HEART));

        assertThatThrownBy(() -> new Cards(cardHand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 패에 중복된 카드가 존재할 수 없습니다.");
    }

    @DisplayName("카드 패에 카드 추가 테스트")
    @Test
    void concat_ReceivingOneCard_ReturnsSizeOf3() {
        List<Card> cardHand = List.of(new Card(Number.ACE, Kind.HEART), new Card(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);

        cards.concat(new Cards(List.of(new Card(Number.JACK, Kind.SPADE))));

        assertThat(cards.getCards().size()).isEqualTo(3);
    }

    @DisplayName("중복 카드 추가 테스트")
    @Test
    void concat_ReceivingDuplicateCard_ThrowsIllegalArgumentException() {
        List<Card> cardHand = List.of(new Card(Number.ACE, Kind.HEART), new Card(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);
        Cards additional = new Cards(List.of(new Card(Number.ACE, Kind.HEART)));

        assertThatThrownBy(() -> cards.concat(additional))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 패에 중복된 카드가 존재할 수 없습니다.");
    }

    @DisplayName("Ace 4장 보유 시 14점 반환")
    @Test
    void getBestPossible_FourAces_Returns14() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.ACE, Kind.DIAMOND),
                new Card(Number.ACE, Kind.CLOVER),
                new Card(Number.ACE, Kind.HEART)));

        assertThat(cards.getBestPossible()).isEqualTo(14);
    }

    @DisplayName("Ace 를 11점으로 판단하여 베스트 점수 계산")
    @Test
    void getBestPossible_ConsideringAceAsEleven_Returns21() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE)));

        assertThat(cards.getBestPossible()).isEqualTo(21);
    }

    @DisplayName("Ace 를 1점으로 판단하여 베스트 점수 계산")
    @Test
    void getBestPossible_ConsideringAceAsOne_Returns21() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.FIVE, Kind.SPADE),
                new Card(Number.SEVEN, Kind.SPADE),
                new Card(Number.EIGHT, Kind.SPADE)));

        assertThat(cards.getBestPossible()).isEqualTo(21);
    }

    @DisplayName("21점 초과 시 Busted")
    @Test
    void isBusted_Over21_ReturnsTrue() {
        Cards cards = new Cards(List.of(
                new Card(Number.SEVEN, Kind.SPADE),
                new Card(Number.EIGHT, Kind.SPADE),
                new Card(Number.NINE, Kind.SPADE)));

        assertThat(cards.isBusted()).isTrue();
    }

    @DisplayName("Ace 와 Jack 만 갖고 있을 경우 BlackJack")
    @Test
    void isBlackJack_ContainingAceAndJack_ReturnsTrue() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.QUEEN, Kind.SPADE)));

        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("Ace 와 Queen 만 갖고 있을 경우 BlackJack")
    @Test
    void isBlackJack_ContainingAceAndQueen_ReturnsTrue() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.JACK, Kind.SPADE)));

        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("Ace 와 King 만 갖고 있을 경우 BlackJack")
    @Test
    void isBlackJack_ContainingAceAndKing_ReturnsTrue() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE)));

        assertThat(cards.isBlackJack()).isTrue();
    }

    @DisplayName("Ace 와 10 만 갖고 있을 경우 BlackJack 이 아님")
    @Test
    void isBlackJack_ContainingAceAndTen_ReturnsFalse() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.TEN, Kind.SPADE)));

        assertThat(cards.isBlackJack()).isFalse();
    }

    @DisplayName("Ace 와 JQK 만 갖고 있지만 2장 이상일 경우 BlackJack 이 아님")
    @Test
    void isBlackJack_ContainingAceAndJackAndKing_ReturnsFalse() {
        Cards cards = new Cards(List.of(
                new Card(Number.ACE, Kind.SPADE),
                new Card(Number.JACK, Kind.SPADE),
                new Card(Number.KING, Kind.SPADE)));

        assertThat(cards.isBlackJack()).isFalse();
    }
}
