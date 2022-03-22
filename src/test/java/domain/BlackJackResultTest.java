package domain;

import static domain.CardFixtures.ACE_SPADES;
import static domain.CardFixtures.FOUR_SPADES;
import static domain.CardFixtures.KING_HEARTS;
import static domain.CardFixtures.TEN_HEARTS;
import static domain.CardFixtures.TWO_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.vo.Name;
import domain.vo.Revenue;
import domain.vo.Wallet;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    @Test
    @DisplayName("딜러와 겜블러를 전달해서 게임 결과를 반환한다")
    void judgeGameResult() {
        // given
        Dealer dealer = new Dealer(List.of(KING_HEARTS, FOUR_SPADES));
        Gamblers gamblers = setupGamblers();
        BlackJackResult blackJackResult = BlackJackResult.of(dealer, gamblers);

        // when
        Map<Name, Revenue> blackjackResult = blackJackResult.getBlackjackResult();
        Revenue revenueOfDealer = blackjackResult.get(Name.from("딜러"));
        Revenue revenueOfPobi = blackjackResult.get(Name.from("포비"));
        Revenue revenueOfDolbum = blackjackResult.get(Name.from("돌범"));
        Revenue revenueOfRichard = blackjackResult.get(Name.from("리차드"));

        // then
        assertAll(
                () -> assertThat(revenueOfDealer.getRevenue()).isEqualTo(-20000),
                () -> assertThat(revenueOfPobi.getRevenue()).isEqualTo(-10000),
                () -> assertThat(revenueOfDolbum.getRevenue()).isEqualTo(30000),
                () -> assertThat(revenueOfRichard.getRevenue()).isEqualTo(0)
        );
    }

    private Gamblers setupGamblers() {
        Gambler pobi = new Gambler(Wallet.of("포비", 10000), List.of(KING_HEARTS, TWO_SPADES));
        Gambler dolbum = new Gambler(Wallet.of("돌범", 20000), List.of(KING_HEARTS, ACE_SPADES));
        Gambler rich = new Gambler(Wallet.of("리차드", 5000), List.of(FOUR_SPADES, TEN_HEARTS));

        return new Gamblers(List.of(pobi, dolbum, rich));
    }
}
