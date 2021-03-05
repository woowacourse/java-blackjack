package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    @DisplayName("라운드 시작 시 랜덤 생성된 카드를 받고, 두장의 카드를 뽑는다.")
    @Test
    void start_round() {
        List<Player> players = Arrays.asList(
                new Player("pobi"),
                new Player("jason")
        );

        Round round = Round.generateWithRandomCards(new Dealer(), players);
        assertThat(round.makeTwoCards()).hasSize(2);
    }
}