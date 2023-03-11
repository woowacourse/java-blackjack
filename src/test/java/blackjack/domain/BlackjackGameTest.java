package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.money.Bank;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class BlackjackGameTest {

    private final Dealer dealer = new Dealer();
    private final List<Player> playerData = new ArrayList<>(
            List.of(new Player("player1"),
                    new Player("player2"))
    );
    private final Players players = new Players(playerData);
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame(dealer, players);
    }

    @Test
    @DisplayName("참가자의 배팅 금액을 Bank에 저장한다.")
    void saveBettingMoney() {
        Player player1 = playerData.get(0);
        Bank bank = blackjackGame.getDealer().getBank();

        blackjackGame.saveBettingMoney(player1, new Money(1000));

        assertThat(bank.findMoney(player1)).isEqualTo(new Money(1000));
    }
}
