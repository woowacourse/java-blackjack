package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.StubDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import dto.BlackjackResult;
import expcetion.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시() {
        // given
        List<Gambler> gamblers = List.of(
                new Gambler("tobi", 1000),
                new Gambler("tobi", 2000)
        );

        // when & then
        assertThatThrownBy(() -> new Gamblers(gamblers))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("딜러 및 사용자 승패 결과 도출")
    void 딜러와_사용자_승패결과_도출() {
        //given
        Dealer dealer = new Dealer();
        Gambler gambler1 = new Gambler("tobi", 10000);
        Gambler gambler2 = new Gambler("quda", 20000);
        Gamblers gamblers = new Gamblers(List.of(gambler1, gambler2)); // 사용자 두명

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); // 딜러
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // tobi
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // quda
        Card sevenDiamond = new Card(CardRank.SEVEN, CardSuit.DIAMOND); //딜러
        Card sevenClover = new Card(CardRank.SEVEN, CardSuit.CLOVER); // tobi
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND); // quda

        StubDeck sd = new StubDeck(List.of(jack, eight, ten, sevenDiamond, sevenClover, nine));
        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        //when
        BlackjackResult result = BlackjackResult.from(gamblers.getResult(dealer));

        //then
        assertThat(result.dealerProfit()).isEqualTo(-10000);
        assertThat(result.matchResultLog().get("tobi")).isEqualTo(-10000);
        assertThat(result.matchResultLog().get("quda")).isEqualTo(20000);
    }
}
