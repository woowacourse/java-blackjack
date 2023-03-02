package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewPlayer() {
        assertDoesNotThrow(() -> new Player(new Name("name")));
    }

    @DisplayName("플레이어에게 카드를 전달하면 플레이어는 카드를 받는다.")
    @Test
    void Should_AddCard_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Player tori = new Player(new Name("tori"));
        tori.addCard(card);
        assertThat(tori.getCards()).contains(card);
    }

    @DisplayName("플레이어는 카드의 합이 21 이상일 경우 카드를 지급 받을 수 없다.")
    @Test
    void Should_isHitFalse_When_MoreThan21() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.TWO, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);
        tori.addCard(card3);

        // when, then
        assertThat(tori.isHit()).isFalse();

    }

    @DisplayName("플레이어는 카드의 합이 21을 초과하지 않는다면 카드를 지급 받을 수 있다.")
    @Test
    void Should_isHitTrue_When_LessThan21() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);

        // when, then
        assertThat(tori.isHit()).isTrue();
    }

    @Test
    void calculateScore() {
    }

    @Test
    void showCards() {
    }
}
