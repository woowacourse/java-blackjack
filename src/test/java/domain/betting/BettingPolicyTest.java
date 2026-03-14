package domain.betting;

import domain.betting.manager.BettingPolicyManager;
import domain.card.*;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CardBundleBuilder;
import utils.TestCardGenerator;

import java.util.List;

public class BettingPolicyTest {

    private final Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
    private final Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
    private final Card spadeNine = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
    private final Card spadeEight = Card.of(CardDenomination.EIGHT, CardEmblem.SPADE);
    private final Card spadeFive = Card.of(CardDenomination.FIVE, CardEmblem.SPADE);
    private CardBundle bustBundle;
    private CardBundle nonBustBundle;
    private CardBundle biggerBundle;
    private CardBundle blackjackBundle;
    private BettingPolicyManager policyManager;

    @BeforeEach
    void setUp() {
        this.blackjackBundle = generateTestCardBundle(cloverAce, spadeJack);
        this.bustBundle = generateTestCardBundle(spadeFive, spadeNine, spadeJack);
        this.nonBustBundle = generateTestCardBundle(spadeFive, spadeEight);
        this.biggerBundle = generateTestCardBundle(spadeEight, spadeNine);
        policyManager = new BettingPolicyManager();
    }

    private CardBundle generateTestCardBundle(Card... card) {
        return new CardBundleBuilder()
                .cards(card)
                .build();
    }

    @Test
    void 플레이어와_딜러가_모두_블랙잭일때_블랙잭푸쉬의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(blackjackBundle);
        Dealer dealer = createDealer(blackjackBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.BLACK_JACK_PUSH;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_블랙잭으로_승리할때_블랙잭의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(blackjackBundle);
        Dealer dealer = createDealer(List.of(spadeFive, spadeEight));
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.BLACK_JACK;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어와_딜러_둘다_버스트일때_플레이어승리의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(bustBundle);
        Dealer dealer = createDealer(bustBundle.openMyCards());

        dealer.dealMyself();
        dealer.hitMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.PLAYER_WIN;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_스탠드일때_플레이어승리의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(nonBustBundle);
        Dealer dealer = createDealer(bustBundle.openMyCards());
        dealer.dealMyself();
        dealer.hitIfRequired();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.PLAYER_WIN;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_버스트일때_플레이어패배의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(bustBundle);
        Dealer dealer = createDealer(nonBustBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.PLAYER_LOSE;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_딜러보다_합이_클때_플레이어승리의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(biggerBundle);
        Dealer dealer = createDealer(nonBustBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.PLAYER_WIN;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_딜러보다_합이_작을때_플레이어패배의_베팅률을_구한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(nonBustBundle);
        Dealer dealer = createDealer(biggerBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingRate.PLAYER_LOSE;

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    private Dealer createDealer(List<Card> cards) {
        TestCardGenerator testCardGenerator = TestCardGenerator.of(cards);
        return Dealer.from(CardDeck.from(testCardGenerator));
    }

}
