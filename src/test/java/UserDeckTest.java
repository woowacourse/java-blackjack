import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDeckTest {
    @Test
    @DisplayName("유저 카드 덱에 카드를 추가할 수 있다.")
    void calculateSumOfUserDeckTest() {
        Card card=new Card();
        UserDeck userDeck=new UserDeck();

        userDeck.pushCard(card);

        assertThat(userDeck.getUserDeck()).contains(card);
    }
}
