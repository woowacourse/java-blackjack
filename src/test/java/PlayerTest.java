import card.GameCardDeck;
import participant.Participant;
import participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 참가자_생성_테스트() {
        //given
        String nickname = "우가";
        Player player = new Player(nickname);

        //when & then
        Assertions.assertThat(player).isInstanceOf(Player.class);
    }

    @Test
    void 플레이어_드로우_가능() {
        //given
        Player player = new Player("우가");

        //when & then
        Assertions.assertThat(player.ableToDraw()).isTrue();
    }

    @Test
    void 플레이어_드로우_실패() {
        //given
        Player player = new Player("우가");
        GameCardDeck gameCardDeck = GameCardDeck.generateFullPlayingCard();
        player.drawCard(gameCardDeck, 12);

        //when & then
        Assertions.assertThat(player.ableToDraw()).isFalse();
    }

    @Test
    void 딜러_확인_실패() {
        //given
        Participant dealer = new Player("우가");

        //when & then
        Assertions.assertThat(dealer.isDealer()).isFalse();
    }
}
