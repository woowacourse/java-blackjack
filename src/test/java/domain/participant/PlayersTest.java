package domain.participant;

import domain.card.Deck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    void 중복된_플레이어_이름은_등록할_수_없다() {
        // given
        List<Player> duplicatedNames = List.of(Player.of("jeje", "1000"), Player.of("mingu", "1000"),
                Player.of("jeje", "10000"));

        // when & then
        Assertions.assertThatThrownBy(() -> Players.from(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 닉네임이_중복되지_않으면_정상적으로_생성되어야_한다() {
        // given
        List<Player> uniqueNames = List.of(Player.of("jeje", "1000"), Player.of("mingu", "1000"),
                Player.of("minseo", "1000"));

        // when & then
        Assertions.assertThat(Players.from(uniqueNames).getPlayers()).hasSize(3);
    }

    @Test
    void getter로_받은_Player는_수정할_수_없다() {
        //given
        Players players = Players.from(
                List.of(Player.of("jeje", "1000"), Player.of("mingu", "1000"), Player.of("minseo", "1000")));
        Deck deck = Deck.createWithAllCards();
        players.giveCardsToEachPlayers(deck, 2);

        Player player = players.getPlayers().get(0);

        players.giveCardsToEachPlayers(deck, 2);

        Assertions.assertThat(player.getCards().size()).isEqualTo(2);
    }
}
