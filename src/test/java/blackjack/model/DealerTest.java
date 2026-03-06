package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    PickStrategy mustPickTen = cards -> Card.opened(Rank.TEN, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱에서 카드를 뽑아서 핸즈에 추가한다.")
    void pickCard() {
        // given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        // when & then
        assertThatCode(() -> dealer.pickAdditionalCard(cardDeck))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        dealer.pickAdditionalCard(cardDeck);
        System.out.println(dealer.getAllCard());
        dealer.pickAdditionalCard(cardDeck);

        System.out.println(dealer.getAllCard());

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러의 총 점수가 21 초과이면 true를 반환한다.")
    void isBust() {
        // given
        CardDeck cardDeck = CardDeck.of(mustPickTen);

        Dealer dealer = Dealer.create();

        dealer.pickAdditionalCard(cardDeck);
        dealer.pickAdditionalCard(cardDeck);
        dealer.pickAdditionalCard(cardDeck);

        // when & then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러랑 플레이어 핸드 비교 결과 테스트")
    void dealerResultTest() {
        // given
        Dealer dealer = Dealer.create();
        Player player = Player.of("player1");

        // when

        // then
    }
}