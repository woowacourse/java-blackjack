package domain.game;

import static domain.card.Denomination.JACK;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.TEN;
import static domain.card.Suits.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    private Users users;
    private BlackJackGame blackJackGame;

    @BeforeEach
    void setUsers() {
        users = Users.from(List.of("hongo"));
        blackJackGame = BlackJackGame.of(users, cards -> {
        });
    }

    @DisplayName("플레이어가 hit을 요청하면 카드를 하나 더 준다")
    @Test
    void hitPlayer() {
        List<Player> players = users.getPlayers();
        Player player = players.get(0);
        int oldSize = player.getCards().size();

        blackJackGame.hitOrStay(true, player);

        assertThat(player.getCards().size()).isEqualTo(oldSize + 1);
    }

    @DisplayName("플레이어가 stay를 요청하면 카드를 더 받지 않는다.")
    @Test
    void stayPlayer() {
        List<Player> players = users.getPlayers();
        Player player = players.get(0);
        int oldSize = player.getCards().size();

        blackJackGame.hitOrStay(false, player);

        assertThat(player.getCards().size()).isEqualTo(oldSize);
    }


    @DisplayName("플레이어가 카드를 더이상 받을 수 없을때 예외가 발생한다.")
    @Test
    void giveCardException() {
        Player player = users.getPlayers().get(0);
        player.hit(Card.of(JACK, HEART));
        player.hit(Card.of(TEN, HEART));

        assertThatThrownBy(() -> blackJackGame.hitOrStay(true, player)).isInstanceOf(IllegalStateException.class)
            .hasMessage("해당 플레이어는 더이상 카드를 받을 수 없습니다.");
    }


    @DisplayName("딜러에게 카드를 한 장 더 준다")
    @Test
    void giveCard_toDealer() {
        int oldCardSize = users.getDealerCards().size();

        blackJackGame.giveCardToDealer();

        assertThat(users.getDealerCards().size()).isEqualTo(oldCardSize + 1);
    }


    @DisplayName("딜러가 카드를 더이상 받을 수 없을때 예외가 발생한다.")
    @Test
    void giveCardToDealerException() {
        users.hitCardToDealer(Card.of(JACK, HEART));
        users.hitCardToDealer(Card.of(QUEEN, HEART));

        assertThatThrownBy(() -> blackJackGame.giveCardToDealer()).isInstanceOf(IllegalStateException.class)
            .hasMessage("딜러는 더이상 카드를 받을 수 없습니다.");
    }
}
