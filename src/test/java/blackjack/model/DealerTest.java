package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    PickStrategy mockStrategy = cards -> new Card(Rank.TEN, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱에서 카드를 뽑아서 핸즈에 추가한다.")
    void pickACard() {
        // given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mockStrategy);

        // when & then
        assertThatCode(() -> dealer.pickACard(cardDeck))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(mockStrategy);


        dealer.pickACard(cardDeck);
        dealer.pickACard(cardDeck);

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("딜러의 총 점수가 21 초과이면 true를 반환한다.")
    void isBust() {
        // given
        CardDeck cardDeck = CardDeck.of(mockStrategy);

        Dealer dealer = Dealer.create();

        dealer.pickACard(cardDeck);
        dealer.pickACard(cardDeck);
        dealer.pickACard(cardDeck);

        // when & then
        assertThat(dealer.isBust()).isTrue();
    }
}