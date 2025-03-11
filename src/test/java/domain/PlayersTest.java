package domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 참여자_인원이_0명인_경우_예외가_발생한다() {
        // given
        Dealer dealer = new Dealer();
        List<User> users = new ArrayList<>();

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(dealer, users))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_인원이_5인_초과인_경우_예외가_발생한다() {
        // given
        Dealer dealer = new Dealer();
        List<User> users = List.of(
                new User("시소"),
                new User("헤일러"),
                new User("부기"),
                new User("사나"),
                new User("수양"),
                new User("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(dealer, users))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어_이름이_중복될_경우_예외가_발생한다() {
        // given
        Dealer dealer = new Dealer();
        List<User> users = List.of(
                new User("시소"),
                new User("헤일러"),
                new User("사나"),
                new User("사나"),
                new User("히스타"),
                new User("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(dealer, users))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_모두에게_카드를_2장씩_분배한다() {
        // given
        Dealer dealer = new Dealer();
        List<User> users = List.of(
                new User("시소"),
                new User("헤일러"),
                new User("부기"),
                new User("사나")
        );
        Players players = new Players(dealer, users);
        Deck deck = DeckGenerator.generateDeck();

        // when
        players.distributeInitialCards(deck);

        // then
        Assertions.assertThat(players.getPlayers().stream()
                .filter(player -> player.getCards().size() == 2)
                .count()).isEqualTo(5);
    }
}