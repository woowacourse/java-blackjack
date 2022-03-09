package blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    @DisplayName("플레이어에게 카드 두장 분배 테스트")
    @Test
    void distribute() {
        List<String> playerNames = List.of("pobi", "jason");
        Blackjack blackjack = new Blackjack(playerNames);
        IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        blackjack.distributeInitialCards(intendedNumberGenerator);
        Person dealer = blackjack.getDealer();
        List<Player> players = blackjack.getPlayers();

        Set<Integer> checker = new HashSet<>();
        int dealerCardSize = dealer.getCards().size();
        checker.add(dealerCardSize);

        players.forEach(player -> checker.add(player.getCards().size()));

        assertThat(checker.size() == 1 && checker.contains(2)).isTrue();
    }
}
