package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UsersTest {

    private static final Cards CARDS_20 = new Cards(List.of(new Card(Shape.HEART, CardNumber.TEN), new Card(Shape.HEART, CardNumber.TEN)));
    private static User 헙크;
    private static User 푸우;
    private static Users HupkAndPooh;

    @BeforeEach
    void setting() {
        헙크 = new User(new Name("헙크"), CARDS_20);
        푸우 = new User(new Name("푸우"), CARDS_20);
        HupkAndPooh = new Users(Arrays.asList(new Name("헙크"), new Name("푸우")), new TestDeck(Arrays.asList(10, 10, 10, 10)));
    }

    @Test
    @DisplayName("Users가 한 명도 없으면 예외가 발생한다.")
    void invalidUsersCountTest() {
        //given
        List<Name> data = new ArrayList<>();

        //when,then
        assertThatThrownBy(() -> new Users(data, new RandomDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 최소 한 명 이상이여야 합니다.");
    }

    @Test
    @DisplayName("Users는 최소 한 명 이상으로 구성되야 한다.")
    void validUsersCountTest() {
        assertDoesNotThrow(() -> new Users(List.of(new Name("헙크")), new RandomDeck()));
    }

    @Test
    @DisplayName("유저 이름으로 유저의 카드들을 가져온다.")
    void getCardsOfTest() {
        //given
        final List<Card> 푸우카드 = HupkAndPooh.finUserByName(new Name("푸우")).getCards().getCards();

        //when
        final Cards cards = 푸우.getCards();
        final List<Card> cardsData = cards.getCards();

        //then
        assertThat(푸우카드.containsAll(cardsData));
    }

}
