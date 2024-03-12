package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import blackjack.exception.NeedRetryException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 플레이어를 생성할 수 없다.")
    @Test
    void validateDuplicate() {
        final List<PlayerName> names = List.of(new PlayerName("kirby"), new PlayerName("kirby"));
        assertThatThrownBy(() -> Players.of(names, new Dealer(Deck.create())))
                .isInstanceOf(NeedRetryException.class)
                .hasMessage("중복된 이름의 참여자는 참여할 수 없습니다.");
    }
}
