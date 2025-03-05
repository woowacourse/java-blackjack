package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("엠제이", new ArrayList<>());
    }

    @DisplayName("이름으로 Player 객체를 생성한다.")
    @Test
    void createAttendee() {
        // given
        String nickname = "pobi";

        // when & then
        assertThatCode(() -> new Player(nickname, new ArrayList<>()))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        List<Card> cards = provideCards(2);

        // when
        player.receiveCards(cards);

        // then
        assertThat(player).isEqualTo(new Player("엠제이", cards));
    }

    @DisplayName("딜러는 카드 2개 중 1개만 보여준다.")
    @Test
    void showDealerCards() {
        // given
        final List<Card> cards = provideCards(2);
        player.receiveCards(cards);
        final List<Card> expected = List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.SPADE, Denomination.TWO));

        // when
        final List<Card> playerCards = player.getInitialCards();

        // then
        assertThat(playerCards).isEqualTo(expected);
    }
}
