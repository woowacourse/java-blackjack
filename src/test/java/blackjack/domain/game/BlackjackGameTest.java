package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.RandomGenerator;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    @Test
    @DisplayName("생성 실패")
    void failed() {
        // given
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        List<BettingAmount> bettingAmounts = List.of(new BettingAmount(1000L));
        RandomGenerator randomGenerator = new RandomGenerator();

        // then
        assertThatThrownBy(() -> new BlackjackGame(names, bettingAmounts, randomGenerator))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 초기화 시 각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        // given
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        List<BettingAmount> bettingAmounts = Arrays.asList(new BettingAmount(1000L), new BettingAmount(1000L));
        RandomGenerator randomGenerator = new RandomGenerator();

        BlackjackGame blackjackGame = new BlackjackGame(names, bettingAmounts, randomGenerator);

        // when
        boolean match = blackjackGame
                .getParticipants()
                .getPlayers()
                .stream()
                .mapToInt(this::calculateCardsSize)
                .anyMatch(cardSize -> cardSize != 2);

        // then
        assertThat(match).isFalse();
    }

    private int calculateCardsSize(Participant participant) {
        return participant
                .getCards()
                .getValue()
                .size();
    }
}
