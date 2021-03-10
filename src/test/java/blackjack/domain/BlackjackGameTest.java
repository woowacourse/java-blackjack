package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

public class BlackjackGameTest {

    private final BlackjackGame blackjackGame = new BlackjackGame();
    private final Participant dealer = new Dealer();
    private List<Participant> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        for (int i = 0; i < 3; i++) {
            Participant player = new Player(i + "");
            player.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
            players.add(player);
        }
    }

}
