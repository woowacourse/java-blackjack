package blackjack.domain;

import static blackjack.domain.testutil.CardDeckFixtureGenerator.createCardDeck;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    Player dealer;
    Player gambler;
    Players players;
    CardDeck cardDeck;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        gambler = new Gambler("돌범", new BetMoney(1000));
        players = new Players(dealer, List.of(gambler));

        cardDeck = createCardDeck(
            new PlayingCard(Suit.CLUBS, Denomination.KING),
            new PlayingCard(Suit.CLUBS, Denomination.JACK),
            new PlayingCard(Suit.HEARTS, Denomination.ACE),
            new PlayingCard(Suit.HEARTS, Denomination.JACK)
        );
    }

    @Test
    @DisplayName("겜블러들의 총 수익에 -1을 곱한 값이 딜러의 수익이 되는지 확인한다.")
    void opposite_blackjackResult() {
        // given &  when
        this.cardDeck.drawTo(gambler);
        this.cardDeck.drawTo(gambler);

        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);

        //when
        final BlackJackResult blackJackResult = BlackJackResult.from(players);

        final double dealerProfit = blackJackResult.calculateDealerProfit();
        final double gamblerProfit = blackJackResult.getValue()
            .values()
            .stream()
            .mapToDouble(Double::valueOf)
            .sum();

        // then
        assertThat(dealerProfit).isEqualTo(-1 * gamblerProfit);
    }

    @Test
    @DisplayName("겜블러가 블랙잭시 배팅 금액의 1.5배를 수익으로 가지는지 확인한다")
    void gambler_blackjack_result() {
        // given &  when
        this.cardDeck.drawTo(gambler);
        this.cardDeck.drawTo(gambler);

        this.cardDeck.drawTo(dealer);
        this.cardDeck.drawTo(dealer);

        //when
        final BlackJackResult blackJackResult = BlackJackResult.from(players);

        final double gamblerProfit = blackJackResult.getValue()
            .values()
            .stream()
            .mapToDouble(Double::valueOf)
            .sum();


        // then
        assertThat(gamblerProfit).isEqualTo(gambler.getBetMoney().times(1.5));
    }
}
