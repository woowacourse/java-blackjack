package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참여인원이 1명 미만일 경우, 예외 처리한다")
    void whenPlayerIsLessThanOneThrowsException() {
        List<Player> players = new ArrayList<>();

        assertThatThrownBy(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("참여인원이 7명 초과일 경우, 예외 처리한다")
    void whenPlayerIsGreaterThanSevenThrowsException() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Card> cardList = new ArrayList<>();

            cardList.add(new Card(Suit.DIAMOND, Face.ACE));
            cardList.add(new Card(Suit.DIAMOND, Face.ACE));

            Player player = new Player(new Cards(cardList), "pobi");
            players.add(player);
        }

        assertThatThrownBy(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }
}
