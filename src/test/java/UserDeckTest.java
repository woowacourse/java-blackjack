import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDeckTest {
    @Test
    @DisplayName("유저 카드 덱에 카드를 추가할 수 있다.")
    void calculateSumOfUserDeckTest() {
        //given
        Card card=new Card();
        UserDeck userDeck=new UserDeck();
        //when
        userDeck.pushCard(card);
        assertThat(userDeck.list).contains(card);
        //then
    }
}
