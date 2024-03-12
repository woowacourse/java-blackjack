package blackjack.domain.participant;

import static blackjack.fixture.PlayerFixture.playerChoco;
import static blackjack.fixture.PlayerFixture.playerClover;
import static blackjack.fixture.TrumpCardFixture.threeSpadeKingCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();
    private Deck deck;
    private Dealer dealer;
    private Player choco;
    private Player clover;

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        choco = playerChoco();
        clover = playerClover();
    }

    @DisplayName("플레이어에게 카드를 더 뽑을지 물어본다.")
    @Test
    void canReceiveCard() {
        //given & when
        IntStream.range(0, 3)
                .forEach(i -> choco.draw(dealer.draw()));

        IntStream.range(0, 6)
                .forEach(i -> clover.draw(dealer.draw()));

        //then
        assertThat(choco.canReceiveCard()).isTrue();
        assertThat(clover.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        Card trumpCard = threeSpadeKingCard();

        //when
        IntStream.range(0, 3)
                .forEach(i -> choco.draw(dealer.draw()));

        System.out.println(choco.getHandCards().get(2).getRank());
        System.out.println(choco.getHandCards().get(2).getSuit());
        //then
        assertThat(choco.getHandCards()).contains(trumpCard);
    }

    @DisplayName("승리 시, 배팅 금액을 받는다.")
    @Test
    void win() {
        //given & when
        choco.win(dealer.getDealerProfit());

        //then
        assertThat(choco.getProfit()).isEqualTo("10000");
        assertThat(dealer.getDealerProfit().toString()).isEqualTo("-10000");
    }

    @DisplayName("패배 시, 배팅 금액을 잃는다.")
    @Test
    void lose() {
        //given & when
        choco.lose(dealer.getDealerProfit());

        //then
        assertThat(choco.getProfit()).isEqualTo("-10000");
        assertThat(dealer.getDealerProfit().toString()).isEqualTo("10000");
    }

    @DisplayName("블랙잭으로 승리 시, 배팅 금액의 1.5배를 받는다.")
    @Test
    void winBlackjack() {
        //given
        dealer.draw(12);
        choco.draw(dealer.draw());
        choco.draw(dealer.draw());

        //when
        choco.win(dealer.getDealerProfit());

        //then
        assertThat(choco.getProfit()).isEqualTo("15000.0");
    }
}
