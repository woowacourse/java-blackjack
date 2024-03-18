package domain;

import static domain.BetAmountFixture.betAmount10_000;
import static domain.BetAmountFixture.betAmount15_000;
import static domain.BetAmountFixture.betAmount20_000;
import static domain.HandsTestFixture.sum10Size2;
import static domain.HandsTestFixture.sum17Size3One;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum21Size2;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("참여자에게 카드 2장을 나눠준다.")
    void dealCards() {
        //given
        final Player player1 = new Player(new Name("레디"), Hands.createEmptyHands(), betAmount10_000);
        final Player player2 = new Player(new Name("제제"), Hands.createEmptyHands(), betAmount10_000);

        final Players players = new Players(List.of(player1, player2));

        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = Dealer.from(cardDeck);

        //when
        dealer.initHands(players);

        //then
        Assertions.assertThat(players.getPlayers()).allMatch(player -> player.handsSize() == 2);
    }

    @Test
    @DisplayName("참여자의 답변이 y라면 카드를 한장 추가한다.")
    void addOneCard() {
        //given
        final Player hitPlayer = new Player(new Name("레디"), Hands.createEmptyHands(), betAmount10_000);
        final Player stayPlayer = new Player(new Name("제제"), Hands.createEmptyHands(), betAmount10_000);

        final Players players = new Players(List.of(hitPlayer, stayPlayer));

        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = Dealer.from(cardDeck);
        dealer.initHands(players);

        //when
        dealer.deal(hitPlayer, Answer.HIT);
        dealer.deal(stayPlayer, Answer.STAY);

        //then
        Assertions.assertThat(hitPlayer.handsSize()).isEqualTo(3);
        Assertions.assertThat(stayPlayer.handsSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 17이상이 될때까지 카드를 추가한다.")
    void dealerDeal() {
        //given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum10Size2);

        //when
        dealer.deal();

        //then
        Assertions.assertThat(dealer.countAddedHands()).isPositive();
        Assertions.assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17);
    }

    @DisplayName("딜러의 카드의 합이 17이상이라면 카드를 추가하지 않는다")
    @Test
    void dealerNoDeal() {
        //given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum18Size2);

        //when
        dealer.deal();

        //then
        Assertions.assertThat(dealer.countAddedHands()).isZero();
        Assertions.assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17);
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void calculateDealerProfit() {
        // given
        final Dealer dealer = new Dealer(CardDeck.generate(), sum20Size2);

        final Player blackJackPlayer = new Player(new Name("제제"), sum21Size2, betAmount10_000);
        final Player losePlayer = new Player(new Name("레디"), sum17Size3One, betAmount20_000);
        final Player tiePlayer = new Player(new Name("피케이"), sum20Size2, betAmount15_000);

        final Players players = new Players(List.of(blackJackPlayer, losePlayer, tiePlayer));

        // when && then
        Assertions.assertThat(dealer.calculateProfitBy(players).getProfit()).isEqualTo(5_000);
    }
}
