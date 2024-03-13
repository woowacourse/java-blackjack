package repository;

import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size3;

import domain.Amount;
import domain.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountRepositoryTest {

    @Test
    @DisplayName("모든 플레이어가 이긴 경우, 최종 수익을 계산한다. (블랙잭인 경우는 없다)")
    void calculateTotalAmountWhenAllPlayersWin() {
        //given
        final Dealer dealer = new Dealer(sum17Size3One);
        final Player winner1 = new Player(new Name("레디"), sum20Size3);
        final Player winner2 = new Player(new Name("제제"), sum21Size3);

        BetAmountRepository repository = new BetAmountRepository(new HashMap<>());
        repository.put(winner1, new BetAmount(1000));
        repository.put(winner2, new BetAmount(2000));

        Map<Player, Amount> expected = Map.of(winner1, new Amount(1000), winner2, new Amount(2000));

        //when
        Map<Player, Amount> result = repository.calculateResult(dealer);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
