package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.dto.PersonStatusResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackServiceTest {

    BlackJackService blackJackService;

    @BeforeEach
    void setUp() {
        blackJackService = new BlackJackService(new MockCardsGenerator(new Card(Suit.DIAMOND, Rank.KING)));
    }

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생해야 한다.")
    void createPeople_duplicateName() {
        // given
        List<String> names = List.of("glen", "pobi", "glen");

        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            blackJackService.createPeople(names);
        }).withMessage("[ERROR] 중복된 이름이 있습니다.");
    }

    @Test
    @DisplayName("참여자가 정상적으로 생성되어야 한다.")
    void createPeople_success() {
        // given
        List<String> names = List.of("glen", "pobi", "encho");

        // when
        blackJackService.createPeople(names);

        // then
        assertThat(blackJackService.getPlayersName())
                .hasSize(3)
                .containsExactly("glen", "pobi", "encho");
    }

    @Test
    @DisplayName("참여자의 이름으로 카드를 뽑을 수 있어야 한다.")
    void drawMoreCardByName_success() {
        // given
        blackJackService.createPeople(List.of("glen", "pobi"));

        // when
        blackJackService.drawMoreCardByName("glen");

        // then
        PersonStatusResponse response = blackJackService.getPersonStatusResponseByName("glen");
        assertThat(response.getCards())
                .hasSize(1);
    }

    @Test
    @DisplayName("참여자의 이름으로 카드를 뽑을 때 점수가 21을 초과하면 예외가 발생해야 한다.")
    void drawMoreCardByName_overScore() {
        // given
        blackJackService.createPeople(List.of("glen", "pobi"));

        // when
        blackJackService.drawMoreCardByName("glen");
        blackJackService.drawMoreCardByName("glen");
        blackJackService.drawMoreCardByName("glen");

        // then
        assertThatIllegalStateException().isThrownBy(() -> {
            blackJackService.drawMoreCardByName("glen");
        }).withMessage("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
    }
}
