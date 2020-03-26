package blackjack.domain.user;

import static blackjack.util.StringUtil.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidPlayerException;
import blackjack.domain.result.BettingMoney;

class PlayerFactoryTest {
    List<BettingMoney> bettingMoney;

    @BeforeEach
    void setUp() {
        bettingMoney = new ArrayList<>(Arrays.asList(BettingMoney.valueOf("1000"), BettingMoney.valueOf("1000"),
            BettingMoney.valueOf("1000")));
    }

    @Test
    void create_PlayerNames_GeneratePlayerList() {
        List<Player> players = Arrays.asList(
            new Player("pobi", BettingMoney.valueOf("1000")),
            new Player("sony", BettingMoney.valueOf("1000")),
            new Player("stitch", BettingMoney.valueOf("1000")));

        assertThat(PlayerFactory.create(parsingPlayerNames("pobi, sony, stitch"), bettingMoney)).isEqualTo(players);
    }

    @Test
    void validateDuplication_DuplicatePlayerNames_InvalidPlayerExceptionThrown() {
        assertThatThrownBy(() -> PlayerFactory.create(parsingPlayerNames("pobi, pobi, stitch"), bettingMoney))
            .isInstanceOf(InvalidPlayerException.class)
            .hasMessage(InvalidPlayerException.DUPLICATE_PLAYER);
    }

    @Test
    void validateDuplication_DuplicateDealerName_InvalidPlayerExceptionThrown() {
        assertThatThrownBy(() -> PlayerFactory.create(parsingPlayerNames("Dealer, pobi, stitch"), bettingMoney))
            .isInstanceOf(InvalidPlayerException.class)
            .hasMessage(InvalidPlayerException.DUPLICATE_DEALER);
    }

    @Test
    void validateSize_PlayerNamesOverMaximumSize_InvalidPlayerExceptionThrown() {
        List<BettingMoney> bettingMonies = Arrays.asList(BettingMoney.valueOf("1000"), BettingMoney.valueOf("1000"),
            BettingMoney.valueOf("1000"), BettingMoney.valueOf("1000"), BettingMoney.valueOf("1000"),
            BettingMoney.valueOf("1000"));
        bettingMoney.addAll(bettingMonies);
        assertThatThrownBy(() -> PlayerFactory.create(
            parsingPlayerNames("sony, pobi, stitch, alt, toney, bibab, turtle, jjinto, kouz"), bettingMoney))
            .isInstanceOf(InvalidPlayerException.class)
            .hasMessage(InvalidPlayerException.SIZE);
    }
}
