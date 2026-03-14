package domain.betting;

import domain.betting.manager.BettingPolicyManager;
import domain.card.*;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import domain.gamer.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardBundleBuilder;
import utils.TestCardGenerator;

import java.util.List;

public class BettingTableTest {

    private final BettingTable bettingTable = new BettingTable(new BettingPolicyManager());
    private final Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
    private final Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
    private final Card spadeNine = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
    private final BettingRate blackJackRate = BettingRate.BLACK_JACK;
    private final BettingRate loseRate = BettingRate.PLAYER_LOSE;
    private final BettingMoney thousandWon = BettingMoney.bet("1000");
    private final Player testPlayer = Player.from(new PlayerName("test"));
    private CardBundle blackJackBundle;
    private CardBundle lossDealerBundle;

    @BeforeEach
    void setUp() {
        blackJackBundle = new CardBundleBuilder()
                .cards(spadeJack, cloverAce)
                .build();

        lossDealerBundle = new CardBundleBuilder()
                .cards(spadeJack, spadeNine)
                .build();
    }

    @Test
    void 베팅테이블에_베팅금액을_올린다() {
        bettingTable.bet(testPlayer, thousandWon);
        BettingMoney actualPlayerMoney = bettingTable.getBettingMoney(testPlayer);
        BettingMoney expectedPlayerMoney = thousandWon.getBettingProfit();

        Assertions.assertThat(actualPlayerMoney)
                .isEqualTo(expectedPlayerMoney);
    }

    @Test
    void 베팅테이블에서_베팅률을_반영한다() {
        testPlayer.addCardBundle(blackJackBundle);
        Players players = Players.from(List.of(testPlayer));
        Dealer dealer = createDealer(lossDealerBundle.openMyCards());
        dealer.dealMyself();

        bettingTable.bet(testPlayer, thousandWon);
        bettingTable.applyBettingRate(dealer, players);

        Profit actualProfit = bettingTable.getPlayerProfit(testPlayer);
        Profit expectedProfit = thousandWon.withRate(blackJackRate.getBettingRate());

        Assertions.assertThat(actualProfit)
                .isEqualTo(expectedProfit);
    }

    @Test
    void 베팅테이블에서_딜러의_수익을_계산한다() {
        testPlayer.addCardBundle(blackJackBundle);
        Players players = Players.from(List.of(testPlayer));
        Dealer dealer = createDealer(lossDealerBundle.openMyCards());
        dealer.dealMyself();

        bettingTable.bet(testPlayer, thousandWon);
        bettingTable.applyBettingRate(dealer, players);

        Profit actualDealerProfit = bettingTable.getDealerProfit();
        Profit expectedDealerProfit = thousandWon
                .withRate(blackJackRate.getBettingRate())
                .reverseProfit();

        Assertions.assertThat(actualDealerProfit)
                .isEqualTo(expectedDealerProfit);
    }

    private Dealer createDealer(List<Card> cards) {
        TestCardGenerator testCardGenerator = TestCardGenerator.of(cards);
        return Dealer.from(CardDeck.from(testCardGenerator));
    }

}
