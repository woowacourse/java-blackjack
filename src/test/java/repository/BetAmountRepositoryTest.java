package repository;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.sum10Size2;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum19Size3Ace1;
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
        repository.put(winner1, new BetAmount(1_000));
        repository.put(winner2, new BetAmount(2_000));

        Map<Player, Amount> expected = Map.of(winner1, new Amount(1_000), winner2, new Amount(2_000));

        //when
        Map<Player, Amount> result = repository.calculateResult(dealer);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("참여자가 블랙잭인 경우 1.5배의 수익을 얻는다.")
    void blackJackAmount() {
        final Dealer dealer = new Dealer(sum17Size3One);
        final Player blackJackPlayer = new Player(new Name("수달"), blackJack);
        final Player loser = new Player(new Name("레디"), sum10Size2);

        BetAmountRepository repository = new BetAmountRepository(new HashMap<>());
        repository.put(blackJackPlayer, new BetAmount(10_000));
        repository.put(loser, new BetAmount(2_000));

        Map<Player, Amount> expected = Map.of(blackJackPlayer, new Amount(15_000), loser, new Amount(-2_000));

        //when
        Map<Player, Amount> result = repository.calculateResult(dealer);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("참가자와 딜러 모두 블랙잭인 경우 배팅 금액을 돌려받는다.")
    void playerAndDealerBlackJack() {
        final Dealer dealer = new Dealer(blackJack);
        final Player blackJackPlayer = new Player(new Name("수달"), blackJack);
        final Player loser = new Player(new Name("레디"), sum18Size2);

        BetAmountRepository repository = new BetAmountRepository(new HashMap<>());
        repository.put(blackJackPlayer, new BetAmount(10_000));
        repository.put(loser, new BetAmount(2_000));

        Map<Player, Amount> expected = Map.of(blackJackPlayer, new Amount(0), loser, new Amount(-2_000));

        //when
        Map<Player, Amount> result = repository.calculateResult(dealer);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 계산한다.")
    void dealerAmount() {
        //given
        final Dealer dealer = new Dealer(sum19Size3Ace1);
        final Player winner1 = new Player(new Name("레디"), sum20Size3);
        final Player winner2 = new Player(new Name("제제"), sum21Size3);
        final Player loser1 = new Player(new Name("브라운"), sum17Size3One);

        BetAmountRepository repository = new BetAmountRepository(new HashMap<>());
        repository.put(winner1, new BetAmount(10_000));
        repository.put(winner2, new BetAmount(30_000));
        repository.put(loser1, new BetAmount(500_000));

        //when
        //TODO 이거 한번에 수행하는 게 더 좋을듯
        repository.calculateResult(dealer);
        final Amount amount = repository.calculateDealerAmount();
        final Amount expected = new Amount(-460_000);

        //then
        Assertions.assertThat(amount).isEqualTo(expected);
    }
}
