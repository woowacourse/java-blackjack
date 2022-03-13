package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;

public class PlayersTest {

    @Test
    @DisplayName("null 로 생성할 수 없다.")
    void createPlayersWithNull() {
        // then
        Assertions.assertThatThrownBy(() -> new Players(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("1명 이상이 Player 로 생성할 수 있다.")
    void createPlayersOverOnePlayer() {
        // then
        Assertions.assertThatThrownBy(() -> new Players(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] Players 생성시 최소 한 명의 Player 가 필요합니다.");
    }

    @Test
    @DisplayName("중복된 이름을 가진 player 들로 생성할 수 없다.")
    void createPlayersWithDuplicatedName() {
        // given
        Name name1 = new Name("lala");
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        Cards cards1 = new Cards(List.of(card1, card2));
        Player player1 = new Player(name1, cards1);

        Card card3 = new Card(Pattern.HEART, Denomination.TEN);
        Card card4 = new Card(Pattern.SPADE, Denomination.TEN);
        Cards cards2 = new Cards(List.of(card3, card4));
        Player player2 = new Player(name1, cards2);
        // then
        Assertions.assertThatThrownBy(() -> new Players(List.of(player1, player2)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] Player 이름은 중복될 수 없습니다.");
    }
}
