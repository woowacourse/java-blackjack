package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideTwoCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
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
        List<Card> cards = provideTwoCards();

        // when
        player.receiveCards(cards);

        // then
        assertThat(player).isEqualTo(new Player("엠제이", cards));
    }
}
