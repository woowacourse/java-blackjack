package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.CardDto;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.carddeck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer = Dealer.create();
        CardDeck tenCloverCardDeck = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));

        dealer.pickAdditionalCard(tenCloverCardDeck);
        dealer.pickAdditionalCard(tenCloverCardDeck);

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }

    @Test
    @DisplayName("첫 카드 뽑기 테스트")
    void pickInitCardsTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Dealer dealer = Dealer.create();

        // when
        dealer.pickInitCards(CardDeck.of(cards -> tenClover));

        // then
        List<CardDto> cards = dealer.getAllCards();
        assertThat(cards).hasSize(2);
        assertThat(cards).containsExactlyInAnyOrder(
                CardDto.from(tenClover),
                CardDto.from(tenClover)
        );
    }

    @Test
    @DisplayName("카드 덱에서 추가 1장 더 가져오기")
    void pickAdditionalTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Dealer dealer = Dealer.create();
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        // when
        dealer.pickAdditionalCard(mustPickTenClover);

        // then
        assertThat(dealer.getAllCards()).hasSize(1);
        assertThat(dealer.getAllCards().getFirst()).isEqualTo(CardDto.from(tenClover));
    }

    @Test
    @DisplayName("버스트 테스트")
    void isBustTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        Dealer dealer = Dealer.create();

        // when
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover);
        dealer.pickAdditionalCard(mustPickTenClover); // pick 30

        // then
        assertThat(dealer.isBust()).isTrue();
    }
}
