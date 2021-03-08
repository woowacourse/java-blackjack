package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsersTest {
    static final String POBI = "pobi";
    private static final String JASON = "jason";
    private static final String INBI = "inbi";
    private static final String MUNGTO = "mungto";

    @DisplayName("Users 생성 테스트")
    @Test
    void createUsers() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(POBI);
        joiner.add(INBI);
        joiner.add(MUNGTO);
        String joinedUsersNames = joiner.toString();

        Users users = new Users(joinedUsersNames);

        assertThat(users.getUsers()).hasSize(3);

        String[] splitUserNames = joinedUsersNames.split(",");
        for (int i = 0; i < 3; i++) {
            assertThat(users.getUsers().get(i).getName().getName())
                .isEqualTo(splitUserNames[i]);
        }
    }

    @DisplayName("게임 결과 반환 테스트 - 딜러가 블랙잭 일 때")
    @Test
    void resultDealerBlackJack() {
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

        User pobi = new User(POBI); // 무승부
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

        User inbi = new User(INBI); // 패
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

        User mungto = new User(MUNGTO); // 패
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

        Users users = new Users(Arrays.asList(pobi, inbi, mungto));

        Map<Name, ResultType> result = users.getResult(dealer);

        assertThat(result.get(new Name(POBI))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name(INBI))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name(MUNGTO))).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("게임 결과 반환 테스트 - 딜러가 bust 일 때")
    @Test
    void resultDealerBust() {
        Dealer dealer = new Dealer(); // bust
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

        User pobi = new User(POBI); // 승
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

        User inbi = new User(INBI); // 승
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

        User mungto = new User(MUNGTO); // 패
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

        Users users = new Users(Arrays.asList(pobi, inbi, mungto));

        Map<Name, ResultType> result = users.getResult(dealer);

        assertThat(result.get(new Name(POBI))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name(INBI))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name(MUNGTO))).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("게임 결과 반환 테스트 - 딜러가 블랙잭이 아닌 21 일 때")
    @Test
    void resultDealer21NotBlackJack() {
        Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

        User pobi = new User(POBI); // 승
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));

        User jason = new User(JASON); // 무승부
        jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

        User inbi = new User(INBI); // 패
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

        User mungto = new User(MUNGTO); // 패
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

        Users users = new Users(Arrays.asList(pobi, jason, inbi, mungto));

        Map<Name, ResultType> result = users.getResult(dealer);

        assertThat(result.get(new Name(POBI))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name(JASON))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name(INBI))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name(MUNGTO))).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("게임 결과 반환 테스트 - 딜러가 21 미만일 때")
    @Test
    void resultDealerUnder21NotBlackJack() {
        Dealer dealer = new Dealer(); // 14
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

        User pobi = new User(POBI); // 승
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.TEN));
        pobi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));

        User jason = new User(JASON); // 무승부
        jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        jason.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));

        User inbi = new User(INBI); // 패
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.FIVE));
        inbi.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));

        User mungto = new User(MUNGTO); // 패
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SEVEN));
        mungto.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));

        Users users = new Users(Arrays.asList(pobi, jason, inbi, mungto));

        Map<Name, ResultType> result = users.getResult(dealer);

        assertThat(result.get(new Name(POBI))).isEqualTo(ResultType.WIN);
        assertThat(result.get(new Name(JASON))).isEqualTo(ResultType.DRAW);
        assertThat(result.get(new Name(INBI))).isEqualTo(ResultType.LOSS);
        assertThat(result.get(new Name(MUNGTO))).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("모든 유저들 랜덤카드 2개 뽑기 테스트")
    @Test
    void usersDrawRandomTwoCards() {
        Users users1 = new Users("pobi,jason,inbi");
        users1.getUsers().forEach(user -> assertThat(user.getCards()).hasSize(0));

        users1.drawRandomTwoCards(Cards.createAllShuffledCards());
        users1.getUsers().forEach(user -> assertThat(user.getCards()).hasSize(2));
    }
}
