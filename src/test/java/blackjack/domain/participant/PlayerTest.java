package blackjack.domain.participant;

import static blackjack.fixture.PlayerFixture.playerChoco;
import static blackjack.fixture.PlayerFixture.playerClover;
import static blackjack.fixture.TrumpCardFixture.threeSpadeKingCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.card.TrumpCard;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();
    private Deck deck;
    private Dealer dealer;
    private Player choco;
    private Player clover;

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        choco = playerChoco(dealer);
        clover = playerClover(dealer);
    }

    @DisplayName("플레이어에게 카드를 더 뽑을지 물어본다.")
    @Test
    void canReceiveCard() {
        //given & when
        choco.draw(dealer);

        IntStream.range(0, 6)
                .forEach(i -> clover.draw(dealer));

        //then
        assertThat(choco.canReceiveCard()).isTrue();
        assertThat(clover.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        TrumpCard trumpCard = threeSpadeKingCard();

        //when
        choco.draw(dealer);

        //then
        assertThat(choco.getHandCards()).contains(trumpCard);
    }
}
