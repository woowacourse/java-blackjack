package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("올바른 게임 승패 결과가 나오는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateResult() {
        Guest guest1 = new Guest(new Name("pobi"));
        Card card1 = new Card(Symbol.SPADE, CardValue.KING);
        Card card2 = new Card(Symbol.CLOVER, CardValue.EIGHT);
        guest1.addCard(card1);
        guest1.addCard(card2);

        Guest guest2 = new Guest(new Name("gugu"));
        Card card5 = new Card(Symbol.SPADE, CardValue.QUEEN);
        Card card6 = new Card(Symbol.CLOVER, CardValue.NINE);
        Card card7 = new Card(Symbol.CLOVER, CardValue.TWO);
        guest2.addCard(card5);
        guest2.addCard(card6);
        guest2.addCard(card7);

        Dealer dealer = new Dealer();
        Card card3 = new Card(Symbol.HEART, CardValue.KING);
        Card card4 = new Card(Symbol.DIAMOND, CardValue.JACK);
        dealer.addCard(card3);
        dealer.addCard(card4);

        Result result = new Result(dealer, List.of(guest1, guest2));

        Map<String, String> guestsResult = result.getGuestsResult();
        Map<String, Integer> dealerResult = result.getDealerResult();

        assertThat(guestsResult.get("pobi")).isEqualTo("패");
        assertThat(guestsResult.get("gugu")).isEqualTo("승");
        assertThat(dealerResult.get("승")).isEqualTo(1);
        assertThat(dealerResult.get("패")).isEqualTo(1);
        assertThat(dealerResult.get("무")).isEqualTo(0);
    }
}
