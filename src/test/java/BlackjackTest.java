import blackjack.domain.Player;
import blackjack.domain.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlackjackTest {

    @Test
    @DisplayName("딜러에게 카드 2장을 발급한다")
    void deal_card_to_dealer() {
        Players players = new Players(List.of("비타"));
        Player dealer = players.getDealer();
        Assertions.assertThat(dealer.getCards().size()).isEqualTo(2);
    }
}
