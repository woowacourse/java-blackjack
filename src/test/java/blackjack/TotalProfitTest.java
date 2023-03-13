package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import common.TestDataGenerator;
import domain.blackjack.BlackjackGame;
import domain.blackjack.TotalProfit;
import domain.card.Card;
import domain.card.Cards;
import domain.card.TrumpCardNumber;
import domain.card.TrumpCardType;
import domain.money.BetAmount;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalProfitTest {
    private static final Card DIAMOND_TEN = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.TEN);
    private static final Card DIAMOND_ACE = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.ACE);
    private static final Card DIAMOND_NINE = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.NINE);
    private static final Card SPADE_NINE = new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE);
    private static final Card DIAMOND_EIGHT = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.EIGHT);

    private Player pobi;
    private Player crong;
    private Player royce;

    @BeforeEach
    void setUp() {
        pobi = Player.of(new ParticipantName("pobi"), BetAmount.from(1000));
        crong = Player.of(new ParticipantName("crong"), BetAmount.from(1000));
        royce = Player.of(new ParticipantName("royce"), BetAmount.from(1000));
    }

    @DisplayName("블랙잭 게임으로부터 딜러와 플레이어의 경합 결과가 생성된다.")
    @Test
    void createDealerResultSuccessTest() {
        Players players = Players.of(pobi, crong, royce);
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame(players);
        blackjackGame.getDealer().start(Cards.of(DIAMOND_TEN, DIAMOND_NINE)); // 19

        pobi.start(Cards.of(DIAMOND_TEN, DIAMOND_ACE)); // 21
        crong.start(Cards.of(DIAMOND_TEN, DIAMOND_TEN)); // 20
        royce.start(Cards.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        TotalProfit totalProfit = TotalProfit.from(blackjackGame);

        assertThat(totalProfit.getProfitBook().values())
                .extracting("amount")
                .containsExactly(1500d, 1000d, -1000d);
    }

    @DisplayName("블랙잭 게임으로부터 딜러와 플레이어의 경합 결과가 생성된다. 무승부 결과 포함")
    @Test
    void createDealerResultSuccessContainsDrawTest() {
        Players players = Players.of(crong, royce);
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame(players);
        blackjackGame.getDealer().start(Cards.of(DIAMOND_TEN, DIAMOND_NINE)); // 19

        crong.start(Cards.of(DIAMOND_TEN, SPADE_NINE)); // 19
        royce.start(Cards.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        TotalProfit totalProfit = TotalProfit.from(blackjackGame);

        assertThat(totalProfit.getProfitBook().values())
                .extracting("amount")
                .containsExactly(0d, -1000d);
    }

    @DisplayName("플레이어의 이익을 총합하여 딜러의 이익을 계산한다.")
    @Test
    void getDealerProfitTest() {
        Players players = Players.of(pobi, crong, royce);
        BlackjackGame blackjackGame = TestDataGenerator.getShuffledBlackjackGame(players);
        blackjackGame.getDealer().start(Cards.of(DIAMOND_TEN, DIAMOND_NINE)); // 19

        pobi.start(Cards.of(DIAMOND_TEN, DIAMOND_ACE)); // 21
        crong.start(Cards.of(DIAMOND_TEN, DIAMOND_TEN)); // 20
        royce.start(Cards.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        TotalProfit totalProfit = TotalProfit.from(blackjackGame);

        assertThat(totalProfit.getProfitBook().values())
                .extracting("amount")
                .containsExactly(1500d, 1000d, -1000d);

        assertThat(totalProfit.getDealerProfit().getAmount())
                .isEqualTo((-1) * (1500d + 1000d + -1000d));
    }
}
