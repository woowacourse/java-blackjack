package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
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
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Player tori = new Player(new Name("tori"));
        tori.addCard(card);
        assertThat(tori.showCards()).contains(card);
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

    @DisplayName("플레이어가 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void Should_Success_When_CalculateScore() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);

        // when, then
        assertThat(tori.calculateScore()).isEqualTo(20);
    }

    @DisplayName("플레이어가 A를 가지고 있을 때 플레이어의 점수 합이 11 이하면 A는 11점으로 간주한다.")
    @Test
    void Should_AIs11_When_ScoreLessThan11() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);

        // when, then
        assertThat(tori.calculateScore()).isEqualTo(21);
    }

    @DisplayName("플레이어가 A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.")
    @Test
    void Should_AIs1_When_ScoreMoreThan11() {
        // given
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);
        tori.addCard(card3);

        // when, then
        assertThat(tori.calculateScore()).isEqualTo(12);
    }

    @DisplayName("플레이어가 가진 카드를 가져올 수 있다.")
    @Test
    void Should_Success_When_ShowCards() {
        // given
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Player tori = new Player(new Name("tori"));

        tori.addCard(card);
        tori.addCard(card2);
        tori.addCard(card3);

        // when, then
        assertThat(tori.showCards()).containsAll(List.of(card,card2,card3));
    }
}
