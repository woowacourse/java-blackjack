package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import controller.Round;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class RoundTest {

    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Order(2)
    @Test
    void startBlackJack() {
        List<String> playerNames = List.of("pobi");
        Dealer dealer = new Dealer();

        List<Gamer> gamers = playerNames.stream()
                .map(Gamer::new)
                .toList();
        Participant participant = new Participant(dealer, gamers);

        Round round = new Round(participant);
        round.initiateGameCondition();

        assertThat(dealer.getHandSize()).isEqualTo(2);
        assertThat(gamers.get(0).getHandSize()).isEqualTo(2);
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Order(1)
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Dealer dealer = new Dealer();

        List<Gamer> gamers = List.of(new Gamer("pobi"));
        Participant participant = new Participant(dealer, gamers);

        Round round = new Round(participant);

        // when
        int count = round.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(5);
    }

    @DisplayName("플레이 중, 카드를 모두 뽑으면 예외가 발생한다.")
    @Order(3)
    @Test
    void pickAllCards() {
        int currentSize = Deck.getTotalSize();
        for (int i = 0; i < currentSize; i++) {
            Deck.pick();
        }

        assertThatThrownBy(Deck::pick)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
