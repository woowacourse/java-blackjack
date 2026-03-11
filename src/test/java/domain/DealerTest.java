package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Rank;
import constant.Suit;
import domain.participant.Dealer;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    class GetOnlyFirstHandTest {

        @Nested
        class Success {

            @Test
            void 딜러의_첫번째_카드만_담긴_손패를_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                Card firstCard = new Card(Rank.TEN, Suit.HEART);
                Card secondCard = new Card(Rank.ACE, Suit.SPADE);
                dealer.addCard(List.of(firstCard));
                dealer.addCard(List.of(secondCard));

                // when
                Hand actual = dealer.getOnlyFirstHand();

                // then
                assertThat(actual.getCardNames())
                    .hasSize(1)
                    .containsExactly(firstCard.getName());
            }
        }
    }
}
