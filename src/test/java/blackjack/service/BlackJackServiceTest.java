package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.Card;
import blackjack.domain.Name;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.dto.ParticipantStatusResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackServiceTest {

    BlackJackService blackJackService;

    @BeforeEach
    void setUp() {
        blackJackService = new BlackJackService();
    }

    @Test
    @DisplayName("참여자가 정상적으로 생성되어야 한다.")
    void createParticipants_success() {
        // given
        List<String> names = List.of("glen", "pobi", "encho");

        // when
        blackJackService.setupGame(new MockDeckGenerator(Card.of(Suit.DIAMOND, Rank.KING), 52), names);

        // then
        assertThat(blackJackService.getPlayersName())
                .hasSize(3)
                .containsExactly(
                        new Name("glen"),
                        new Name("pobi"),
                        new Name("encho")
                );
    }

    @Test
    @DisplayName("참여자가 생성되면 카드를 2장 소지하고 있어야 한다.")
    void createParticipants_haveTwoCard() {
        // given
        List<String> names = List.of("glen");

        // when
        blackJackService.setupGame(new ShuffledDeckGenerator(), names);

        // then
        assertThat(blackJackService.getParticipantStatusResponseByName(new Name("glen")).getCards())
                .hasSize(2);
    }

    @Test
    @DisplayName("참여자의 이름으로 카드를 뽑을 수 있어야 한다.")
    void drawMoreCardByName_success() {
        // given
        blackJackService.setupGame(new ShuffledDeckGenerator(), List.of("glen", "pobi"));
        Name name = new Name("glen");

        // when
        blackJackService.drawMoreCardByName(name);

        // then
        ParticipantStatusResponse response = blackJackService.getParticipantStatusResponseByName(name);
        assertThat(response.getCards())
                .hasSize(3);
    }

    @Test
    @DisplayName("참여자의 이름으로 카드를 뽑을 때 점수가 21을 초과하면 예외가 발생해야 한다.")
    void drawMoreCardByName_overScore() {
        // given
        blackJackService.setupGame(new MockDeckGenerator(Card.of(Suit.DIAMOND, Rank.KING), 52),
                List.of("glen", "pobi"));
        Name name = new Name("glen");

        // when
        blackJackService.drawMoreCardByName(name);

        // then
        assertThatIllegalStateException().isThrownBy(() -> {
            blackJackService.drawMoreCardByName(name);
        }).withMessage("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
    }
}
