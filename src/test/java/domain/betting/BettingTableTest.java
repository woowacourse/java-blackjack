package domain.betting;

import domain.analyzer.BettingResult;
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
    Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
    Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
    Card spadeNine = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
    CardBundle blackJackBundle;
    CardBundle lossDealerBundle;
    BettingRate blackJackRate = BettingResult.BLACK_JACK.bettingRate();
    BettingRate loseRate = BettingResult.COMPARE_LOSE.bettingRate();
    Money thousandWon = Money.from("1000");
    Player testPlayer = Player.from(new PlayerName("test"));

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
        BettingTable bettingTable = new BettingTable();

        bettingTable.bet(testPlayer, thousandWon);
        Money actualPlayerMoney = bettingTable.getPlayerProfit(testPlayer);
        Money expectedPlayerMoney = thousandWon.getMoney();

        Assertions.assertThat(actualPlayerMoney)
                .isEqualTo(expectedPlayerMoney);
    }

    @Test
    void 베팅테이블에서_베팅률을_반영한다() {
        BettingTable bettingTable = new BettingTable();
        testPlayer.addCardBundle(blackJackBundle);
        Players players = Players.from(List.of(testPlayer));
        Dealer dealer = createDealer(lossDealerBundle.openMyCards());
        dealer.dealMyself();

        bettingTable.bet(testPlayer, thousandWon);
        bettingTable.applyBettingRate(dealer, players);

        Money actualProfit = bettingTable.getPlayerProfit(testPlayer);
        Money expectedProfit = thousandWon.applyBettingRate(blackJackRate);

        Assertions.assertThat(actualProfit)
                .isEqualTo(expectedProfit);
    }

    @Test
    void 베팅테이블에서_딜러의_수익을_계산한다() {
        BettingTable bettingTable = new BettingTable();
        testPlayer.addCardBundle(blackJackBundle);
        Players players = Players.from(List.of(testPlayer));
        Dealer dealer = createDealer(lossDealerBundle.openMyCards());
        dealer.dealMyself();

        bettingTable.bet(testPlayer, thousandWon);
        bettingTable.applyBettingRate(dealer, players);

        Money actualDealerProfit = bettingTable.getDealerProfit();
        Money expectedDealerProfit = thousandWon
                .applyBettingRate(blackJackRate)
                .applyBettingRate(loseRate);

        Assertions.assertThat(actualDealerProfit)
                .isEqualTo(expectedDealerProfit);
    }

    private Dealer createDealer(List<Card> cards) {
        TestCardGenerator testCardGenerator = TestCardGenerator.of(cards);
        Dealer dealer = Dealer.from(CardDeck.from(testCardGenerator));
        return dealer;
    }
}
