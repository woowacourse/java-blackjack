package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.BETTING_1000;

import blackjack.domain.card.RandomGenerator;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    @Test
    @DisplayName("각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        // given
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        List<BettingAmount> bettingAmounts = Arrays.asList(BETTING_1000, BETTING_1000);
        RandomGenerator randomGenerator = new RandomGenerator();

        BlackjackGame blackjackGame = new BlackjackGame(createPlayersInfo(names, bettingAmounts), randomGenerator);

        // when
        blackjackGame.initCardsAllParticipants();

        // then
        boolean match = blackjackGame
                .getParticipants()
                .getPlayers()
                .stream()
                .mapToInt(this::calculateCardsSize)
                .anyMatch(cardSize -> cardSize != 2);
        assertThat(match).isFalse();
    }

    private Map<Name, BettingAmount> createPlayersInfo(List<Name> names, List<BettingAmount> bettingAmounts) {
        Map<Name, BettingAmount> map = new HashMap<>();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            map.put(names.get(i), bettingAmounts.get(i));
        }
        return map;
    }

    private int calculateCardsSize(Participant participant) {
        return participant
                .getCards()
                .getValue()
                .size();
    }
}
