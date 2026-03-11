package domain.betting;

import domain.analyzer.BettingResult;
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
    private CardBundle blackjackBundle;
    private BettingPolicyManager policyManager;

    @BeforeEach
    void setUp() {
        this.blackjackBundle = generateTestCardBundle(cloverAce, spadeJack);
        this.bustBundle = generateTestCardBundle(spadeFive, spadeNine, spadeJack);
        this.nonBustBundle = generateTestCardBundle(spadeFive, spadeEight);
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
        TestCardGenerator testCardGenerator = TestCardGenerator.of(List.of(cloverAce, spadeJack));
        Dealer dealer = Dealer.from(CardDeck.from(testCardGenerator));
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
        TestCardGenerator testCardGenerator = TestCardGenerator.of(List.of(spadeFive, spadeEight));
        Dealer dealer = Dealer.from(CardDeck.from(testCardGenerator));
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
        TestCardGenerator testCardGenerator = TestCardGenerator.of(bustBundle.openMyCards());
        Dealer dealer = Dealer.from(CardDeck.from(testCardGenerator));
        dealer.dealMyself();
        dealer.hitMyself();

        BettingRate actualBettingRate = policyManager.gainBettingRate(dealer, testPlayer);
        BettingRate expectedBettingRate = BettingResult.DOUBLE_BUST.bettingRate();

        Assertions.assertThat(actualBettingRate)
                .isEqualTo(expectedBettingRate);
    }

}
