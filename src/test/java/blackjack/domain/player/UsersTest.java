package blackjack.domain.player;

import static blackjack.domain.ResultType.DRAW;
import static blackjack.domain.ResultType.LOSS;
import static blackjack.domain.ResultType.WIN_NOT_WITH_BLACKJACK;
import static blackjack.domain.ResultType.WIN_WITH_BLACKJACK;
import static blackjack.domain.player.UserTest.MAX_BETTING_MONEY_BOUND;
import static blackjack.domain.player.UserTest.MIN_BETTING_MONEY_BOUND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsersTest {
    static final String POBI = "pobi";
    private static final String JASON = "jason";
    private static final String INBI = "inbi";
    private static final String MUNGTO = "mungto";

    static List<Integer> bettingMonies = new ArrayList<>(
        Arrays.asList(
            MIN_BETTING_MONEY_BOUND,
            50_000,
            12_345_678,
            MAX_BETTING_MONEY_BOUND
        )
    );

    @DisplayName("Users 생성 테스트")
    @Test
    void createUsers() {
        List<User> usersInput = Arrays.asList(
            new User(POBI),
            new User(JASON),
            new User(INBI),
            new User(MUNGTO)
        );

        Users users = new Users(usersInput);
        assertThat(users.getUsers()).hasSize(4);

        for (int i = 0; i < 3; i++) {
            assertThat(users.getUsers().get(i).getName().getName())
                .isEqualTo(usersInput.get(i).getName().getName());
        }
    }

    @DisplayName("게임 결과 수익 반환 테스트 - 딜러가 블랙잭 일 때")
    @Test
    void profitsDealerBlackJack() {
        for (int bettingMoney : bettingMonies) {
            Dealer dealer = new Dealer(); // 블랙잭
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

            User pobi = new User(POBI, bettingMoney); // 무승부
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

            User inbi = new User(INBI, bettingMoney); // 패
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

            User mungto = new User(MUNGTO, bettingMoney); // 패
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

            Users users = new Users(Arrays.asList(pobi, inbi, mungto));
            Map<Name, Integer> profits = users.getProfits(dealer);

            assertProfits(profits, Arrays.asList(POBI, INBI, MUNGTO),
                Arrays.asList(DRAW, LOSS, LOSS), bettingMoney);
        }
    }

    private void assertProfits(Map<Name, Integer> profits, List<String> userNames,
        List<ResultType> expectedResult, int bettingMoney) {

        for (int i = 0; i < userNames.size(); i++) {
            int actualProfit = profits.get(new Name(userNames.get(i)));
            if (expectedResult.get(i) == WIN_WITH_BLACKJACK) {
                assertThat(actualProfit).isEqualTo((int) (1.5 * (double) bettingMoney));
            }
            if (expectedResult.get(i) == WIN_NOT_WITH_BLACKJACK) {
                assertThat(actualProfit).isEqualTo(bettingMoney);
            }
            if (expectedResult.get(i) == DRAW) {
                assertThat(actualProfit).isEqualTo(0);
            }
            if (expectedResult.get(i) == LOSS) {
                assertThat(actualProfit).isEqualTo(-bettingMoney);
            }
        }
    }

    @DisplayName("게임 결과 수익 반환 테스트 - 딜러가 bust 일 때")
    @Test
    void profitsDealerBust() {
        for (int bettingMoney : bettingMonies) {
            Dealer dealer = new Dealer(); // bust
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

            User pobi = new User(POBI, bettingMoney); // 블랙잭 승
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

            User inbi = new User(INBI, bettingMoney); // 블랙잭이 아닌 승
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

            User mungto = new User(MUNGTO, bettingMoney); // 패
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

            Users users = new Users(Arrays.asList(pobi, inbi, mungto));
            Map<Name, Integer> profits = users.getProfits(dealer);

            assertProfits(profits, Arrays.asList(POBI, INBI, MUNGTO),
                Arrays.asList(WIN_WITH_BLACKJACK, WIN_NOT_WITH_BLACKJACK, LOSS), bettingMoney);
        }
    }

    @DisplayName("게임 결과 수익 반환 테스트 - 딜러가 블랙잭이 아닌 21 일 때")
    @Test
    void profitsDealer21NotBlackJack() {
        for (int bettingMoney : bettingMonies) {
            Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

            User pobi = new User(POBI, bettingMoney); // 블랙잭 승
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

            User jason = new User(JASON, bettingMoney); // 무승부
            jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

            User inbi = new User(INBI, bettingMoney); // 패
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

            User mungto = new User(MUNGTO, bettingMoney); // 패
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

            Users users = new Users(Arrays.asList(pobi, jason, inbi, mungto));
            Map<Name, Integer> profits = users.getProfits(dealer);

            assertProfits(profits, Arrays.asList(POBI, JASON, INBI, MUNGTO),
                Arrays.asList(WIN_WITH_BLACKJACK, DRAW, LOSS, LOSS), bettingMoney);
        }
    }

    @DisplayName("게임 결과 수익 반환 테스트 - 딜러가 21 미만일 때")
    @Test
    void profitsDealerUnder21NotBlackJack() {
        for (int bettingMoney : bettingMonies) {
            Dealer dealer = new Dealer(); // 14
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

            User pobi = new User(POBI, bettingMoney); // 블랙잭이 아닌 승
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
            pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));

            User jason = new User(JASON, bettingMoney); // 무승부
            jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

            User inbi = new User(INBI, bettingMoney); // 패
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
            inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

            User mungto = new User(MUNGTO, bettingMoney); // 패
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
            mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

            Users users = new Users(Arrays.asList(pobi, jason, inbi, mungto));
            Map<Name, Integer> profits = users.getProfits(dealer);

            assertProfits(profits, Arrays.asList(POBI, JASON, INBI, MUNGTO),
                Arrays.asList(WIN_NOT_WITH_BLACKJACK, DRAW, LOSS, LOSS), bettingMoney);
        }
    }

    @DisplayName("모든 유저들 랜덤카드 2개 뽑기 테스트")
    @Test
    void usersDrawRandomTwoCards() {
        List<User> usersInput = Arrays.asList(
            new User(POBI),
            new User(JASON),
            new User(INBI),
            new User(MUNGTO)
        );

        Users users = new Users(usersInput);
        users.getUsers().forEach(user -> assertThat(user.getCards()).hasSize(0));

        users.drawRandomTwoCards(Cards.createAllShuffledCards());
        users.getUsers().forEach(user -> assertThat(user.getCards()).hasSize(2));
    }
}
