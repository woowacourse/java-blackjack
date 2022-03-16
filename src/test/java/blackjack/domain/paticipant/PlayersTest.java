package blackjack.domain.paticipant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 생성_시_null_players_예외발생() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("players는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_중복이름_예외발생() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = Arrays.asList("name", "name");

        assertThatThrownBy(() -> new Players(names, text -> 1000, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 간에 중복이 있으면 안됩니다.");
    }

    @Test
    void 생성_시_유저가_없는_경우_예외발생() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        final List<String> names = new ArrayList<>();

        assertThatThrownBy(() -> new Players(names, text -> 1000, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 0명이 될 수 없습니다.");
    }
}
