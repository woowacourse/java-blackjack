package domain.betting;

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

    private final Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
    private final Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
    private final Card spadeNine = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
    private final BettingRate blackJackRate = BettingResult.BLACK_JACK.bettingRate();
    private final BettingRate loseRate = BettingResult.PLAYER_LOSE.bettingRate();
    private final Money thousandWon = Money.from("1000");
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
        return Dealer.from(CardDeck.from(testCardGenerator));
    }

}
