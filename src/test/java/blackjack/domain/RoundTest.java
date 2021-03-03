package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    @DisplayName("라운드 시작 시 랜덤 생성된 카드를 받고, 두장의 카드를 뽑는다.")
    @Test
    void start_round() {
        Card spadeCard = Card.of("스페이드", "10");
        Card heartCard = Card.of("하트", "A");
        List<Card> cards = Arrays.asList(
                spadeCard, heartCard
        );

        List<Player> players = Arrays.asList(
                new Player("pobi"),
                new Player("jason")
        );
        Round round = new Round(cards, new Dealer(), players);
        assertThat(round.makeTwoCards()).containsExactly(spadeCard, heartCard);
    }
}