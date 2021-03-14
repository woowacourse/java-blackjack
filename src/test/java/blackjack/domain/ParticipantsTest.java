package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static blackjack.domain.Fixture.*;
import static blackjack.domain.Fixture.CARDS_SCORE_21;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들을 정상적으로 생성하는 지 체크")
    public void init() {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(
            new Player("jason"),
            new Player("pobi")
        );
        Participants participants = Participants.of(dealer, players);
        assertThat(participants.getParticipants()).contains(dealer);
        for (Player player : players) {
            assertThat(participants.getParticipants()).contains(player);
        }
    }

    @Test
    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    public void validateOverlappedNames() {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(
            new Player("jason"),
            new Player("jason")
        );

        assertThatCode(() -> {
            Participants.of(dealer, players);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }

    @Test
    @DisplayName("플레이어들이 카드를 2장씩 받는다.")
    public void receiveDefaultCards() {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(
            new Player("jason")
        );
        CardDeck cardDeck = new CardDeck();
        Participants participants = Participants.of(dealer, players);
        Participant jason = participants.toList()
                                        .get(0);
        List<Card> jasonCards = jason.getCards();
        participants.receiveDefaultCards(cardDeck);
        int afterSize = jasonCards.size();
        assertThat(afterSize).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자들의 수익을 제대로 계산하는 지 테스트")
    public void calculateFinalBetProfit_1() {
        Player player1 = new Player("json");
        player1.initBetAmount(10000);
        Player player2 = new Player("pobi");
        player2.initBetAmount(20000);
        List<Player> players = Arrays.asList(
                player1,
                player2
        );
        Dealer dealer = new Dealer();
        player1.receiveCards(new Cards(CARDS_SCORE_20));
        player2.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_21));
        Participants participants = Participants.of(dealer, players);
        Map<String, BetAmount> result = participants.calculateFinalBetProfits();
        assertThat(result.get("딜러")).isEqualTo(new BetAmount(30000));
        assertThat(result.get("json")).isEqualTo(new BetAmount(-10000));
        assertThat(result.get("pobi")).isEqualTo(new BetAmount(-20000));
    }
}
