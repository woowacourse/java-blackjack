package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerGameResultTest {
    @DisplayName("딜러의 게임 결과 생성 테스트")
    @Test
    void testCreateDealerGameResult() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = Arrays.asList(
                new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.QUEEN)
        );
        //when
        DealerGameResult dealerGameResult = new DealerGameResult(dealer, dealerCards);

        //then
        assertThat(dealerGameResult).isEqualTo(new DealerGameResult(new Dealer(), Arrays.asList(
                new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.QUEEN))
        ));
    }

    @DisplayName("딜러의 이름 반환 테스트")
    @Test
    void testGetDealerName() {
        //given
        Dealer dealer = new Dealer();

        //when
        DealerGameResult dealerGameResult = new DealerGameResult(dealer, Arrays.asList());

        //then
        assertThat(dealerGameResult.getDealerName()).isEqualTo("딜러");
    }

    @DisplayName("딜러의 승무패를 구하는 기능 테스트")
    @Test
    void testDealersWinOrLoses() {
        //given
        User first = new User(Name.from("욘"));
        User second = new User(Name.from("웨지"));
        User third = new User(Name.from("포비"));
        User fourth = new User(Name.from("제이슨"));

        double firstUserGameResult = Profit.DRAW.getProfit(); // 0
        double secondUserGameResult = Profit.WIN.getProfit(); // 1
        double thirdUserGameResult = Profit.LOSE.getProfit(); // -1
        double fourthUserGameResult = Profit.LOSE.getProfit(); // -1

        Map<User, Double> temp = new HashMap<>();
        temp.put(first, firstUserGameResult);
        temp.put(second, secondUserGameResult);
        temp.put(third, thirdUserGameResult);
        temp.put(fourth, fourthUserGameResult);


        List<Card> dealerCards = Arrays.asList(
                new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                new Card(Suit.HEART, Value.ACE));

        DealerGameResult dealerGameResult = new DealerGameResult(new Dealer(), dealerCards);

        //when
        UserGameResult userGameResult = new UserGameResult(temp);

        //then
        assertThat(dealerGameResult.calculateDealerProfit(userGameResult)).isEqualTo(1);
    }
}
