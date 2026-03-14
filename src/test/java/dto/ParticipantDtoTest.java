package dto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantDtoTest {

    @Nested
    class FromDealerTest {

        @Test
        void 딜러_정보를_모든_패와_점수로_생성한다() {
            // given
            Dealer dealer = new Dealer();
            dealer.addCard(card(Rank.TEN, Suit.HEART));
            dealer.addCard(card(Rank.SEVEN, Suit.SPADE));

            // when
            ParticipantDto actual = ParticipantDto.from(dealer);

            // then
            assertThat(actual.name()).isEqualTo("딜러");
            assertThat(actual.hand()).containsExactly("10하트", "7스페이드");
            assertThat(actual.score()).isEqualTo(17);
        }

        @Test
        void 딜러_초기_공개_정보를_생성하면_첫_번째_카드만_노출한다() {
            // given
            Dealer dealer = new Dealer();
            dealer.addCard(card(Rank.ACE, Suit.CLOVER));
            dealer.addCard(card(Rank.K, Suit.DIAMOND));

            // when
            ParticipantDto actual = ParticipantDto.from(dealer, true);

            // then
            assertThat(actual.name()).isEqualTo("딜러");
            assertThat(actual.hand()).containsExactly("A클로버");
            assertThat(actual.score()).isEqualTo(21);
        }
    }

    @Nested
    class FromPlayerTest {

        @Test
        void 플레이어_정보를_패와_점수로_생성한다() {
            // given
            Player player = new Player("jacob");
            player.addCard(card(Rank.NINE, Suit.HEART));
            player.addCard(card(Rank.EIGHT, Suit.CLOVER));

            // when
            ParticipantDto actual = ParticipantDto.from(player);

            // then
            assertThat(actual.name()).isEqualTo("jacob");
            assertThat(actual.hand()).containsExactly("9하트", "8클로버");
            assertThat(actual.score()).isEqualTo(17);
        }
    }

    private static Card card(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }
}
