package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.TrumpCard;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static blackjack.fixture.PlayerFixture.playerChoco;
import static blackjack.fixture.PlayerFixture.playerClover;
import static blackjack.fixture.TrumpCardFixture.threeSpadeKingCard;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("버스트 되지 않은 플레이어는 카드를 한장 더 뽑을 수 있다.")
    @Test
    void canReceiveCard() {
        // when
        choco.draw(dealer);

        //then
        assertThat(choco.canReceiveCard()).isTrue();
    }

    @DisplayName("버스트 된 플레이어는 카드를 한장 더 뽑을 수 없다.")
    @Test
    void cantReceiveCard() {
        // when
        IntStream.range(0, 6)
                .forEach(i -> clover.draw(dealer));

        //then
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
