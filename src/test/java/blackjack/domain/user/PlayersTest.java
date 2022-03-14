package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import blackjack.domain.card.deck.OnlyTenSpadePickDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 클래스는 player 들을 입력받으면 정상적으로 생성된다.")
    void create_players() {
        User aki = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        User alien = mock(User.class, withSettings().useConstructor(new UserName("alien")).defaultAnswer(CALLS_REAL_METHODS));
        List<User> users = List.of(aki, alien);

        assertThatCode(() -> new Players(users)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("takeInitHands 메서드를 통해 모든 플레이어들이 처음에 2장의 카드를 받는다.")
    void init_cards_players() {
        User aki = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        Players players = new Players(List.of(aki));
        players.takeInitHand(new OnlyTenSpadePickDeck());

        assertThat(aki.getCards().get()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되면 에러를 출력한다.")
    void duplicate_name_error() {
        User aki1 = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));
        User aki2 = mock(User.class, withSettings().useConstructor(new UserName("aki")).defaultAnswer(CALLS_REAL_METHODS));

        assertThatThrownBy(() -> new Players(List.of(aki1, aki2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름이 딜러면 에러를 출력한다.")
    void same_dealer_name_error() {
        User dealerNameUser = mock(User.class, withSettings().useConstructor(new UserName("딜러")).defaultAnswer(CALLS_REAL_METHODS));

        assertThatThrownBy(() -> new Players(List.of(dealerNameUser)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
