package blackjack.fixture;

import blackjack.domain.batting.BattingAmount;
import blackjack.domain.batting.BattingAmountRepository;
import blackjack.domain.participant.Player;

public class BattingAmountRepositoryFixture {

    public static BattingAmountRepository 배팅_금액_정보(final Player player, final int battingAmount) {
        final BattingAmountRepository repository = new BattingAmountRepository();
        repository.save(player, new BattingAmount(battingAmount));

        return repository;
    }
}
