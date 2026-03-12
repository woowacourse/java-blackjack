package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BettingMoney;
import domain.FixedDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import dto.BlackjackResult;
import exception.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시() {
        //given
        Gambler tobiOriginal = new Gambler("tobi", new BettingMoney(1000));
        Gambler tobiFake = new Gambler("tobi", new BettingMoney(1000));
        List<Gambler> gamblers = List.of(tobiOriginal, tobiFake);

        //when & then
        assertThatThrownBy(() -> new Gamblers(gamblers))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("딜러 및 사용자 승패 결과 도출")
    void 딜러와_사용자_승패결과_도출() {
        //given
        Dealer dealer = new Dealer();
        Gambler tobi = new Gambler("tobi", new BettingMoney(1000));
        Gambler quda = new Gambler("quda", new BettingMoney(1000));

        Gamblers gamblers = new Gamblers(List.of(tobi, quda));// 사용자 두명

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); // 딜러
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // tobi
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // quda
        Card sevenDiamond = new Card(CardRank.SEVEN, CardSuit.DIAMOND); //딜러
        Card sevenClover = new Card(CardRank.SEVEN, CardSuit.CLOVER); // tobi
        Card nine = new Card(CardRank.NINE, CardSuit.DIAMOND); // quda

        FixedDeck sd = new FixedDeck(List.of(jack, eight, ten, sevenDiamond, sevenClover, nine));
        dealer.deal(sd);
        gamblers.dealAll(sd);
        dealer.deal(sd);
        gamblers.dealAll(sd);

        //when
        BlackjackResult result = gamblers.getResult(dealer);

        //then
        assertThat(result.winCount()).isEqualTo(1);
        assertThat(result.lossCount()).isEqualTo(1);
        assertThat(result.drawCount()).isEqualTo(0);
        assertThat(result.logs().get(0)).isEqualTo("tobi:패");
        assertThat(result.logs().get(1)).isEqualTo("quda:승");
    }
}
