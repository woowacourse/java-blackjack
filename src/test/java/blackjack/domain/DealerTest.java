package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.player.Hand;
import fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러 도메인 테스트")
class DealerTest {

    @DisplayName("딜러 핸드에 카드를 추가할 수 있다")
    @Test
    void testAppendCardToPlayer() {
        List<Card> hand = new ArrayList<>();
        Dealer dealer = new Dealer(new Hand(hand));

        Card card = CardFixture.from(2);
        dealer.appendCard(card);

        assertThat(hand).containsExactly(card);
    }
}