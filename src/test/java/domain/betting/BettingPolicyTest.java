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

    private Card cloverAce = Card.of(CardDenomination.ACE, CardEmblem.CLOVER);
    private Card spadeJack = Card.of(CardDenomination.JACK, CardEmblem.SPADE);
    private Card spadeNine = Card.of(CardDenomination.NINE, CardEmblem.SPADE);
    private Card spadeEight = Card.of(CardDenomination.EIGHT, CardEmblem.SPADE);
    private Card spadeFive = Card.of(CardDenomination.FIVE, CardEmblem.SPADE);
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
    void 블랙잭_푸쉬가일어나는_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(blackjackBundle);
        Dealer dealer = createDealer(blackjackBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.BLACK_JACK_PUSH.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 블랙잭_승리가_일어나는_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(blackjackBundle);
        Dealer dealer = createDealer(List.of(spadeFive, spadeEight));
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.BLACK_JACK.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어와_딜러_둘다_버스트인_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(bustBundle);
        Dealer dealer = createDealer(bustBundle.openMyCards());

        dealer.dealMyself();
        dealer.hitMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.DOUBLE_BUST.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_스탠드인_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(nonBustBundle);
        Dealer dealer = createDealer(bustBundle.openMyCards());
        dealer.dealMyself();
        dealer.hitIfRequired();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.DEALER_BUST.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_버스트인_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(bustBundle);
        Dealer dealer = createDealer(nonBustBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.PLAYER_BUST.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_딜러보다_합이_큰_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(biggerBundle);
        Dealer dealer = createDealer(nonBustBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.COMPARE_WIN.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    @Test
    void 플레이어가_딜러보다_합이_작은_경우를_테스트한다() {
        Player testPlayer = Player.from(new PlayerName("test"));
        testPlayer.addCardBundle(nonBustBundle);
        Dealer dealer = createDealer(biggerBundle.openMyCards());
        dealer.dealMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.COMPARE_LOSE.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

    private Dealer createDealer(List<Card> cards) {
        TestCardGenerator testCardGenerator = TestCardGenerator.of(cards);
        Dealer dealer = Dealer.from(CardDeck.from(testCardGenerator));
        return dealer;
    }

}
