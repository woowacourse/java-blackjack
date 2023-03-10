package domain.game;

import domain.CardShuffler;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantMoney;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    private Dealer dealer;
    private GameManager gameManager;
    private Map<Participant, BettingMoney> playerInfo;

    @BeforeEach
    void init() {
        dealer = Participant.createDealer();
        playerInfo = new LinkedHashMap<>(Map.ofEntries(Map.entry(Player.create("pobi"), BettingMoney.create("10000")),
                Map.entry(Player.create("crong"), BettingMoney.create("20000"))));
        gameManager = GameManager.create(dealer, playerInfo, (card) -> card);
    }

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다")
    void create_givenDeckAndParticipants_thenSuccess() {
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create(dealer, playerInfo, (card) -> card));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("giveCards()는 호출하면, 카드를 건네준다")
    void giveCards_whenCall_thenSuccess() {
        assertThatCode(() -> gameManager.handFirstCards(dealer))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("getFirstBettingResult()는 딜러가 블랙잭이면, 모든 참가자의 베팅 금액을 0원으로 만든다.")
    void getFirstBettingResult_givenDealerAndParticipantMoney_thenReturnParticipantMoney() {
        // given
        final CardShuffler mockCardShuffler = createBlackCardShuffler();
        final GameManager gameManager = GameManager.create(dealer, playerInfo, mockCardShuffler);
        ParticipantMoney moneyAfterHand = gameManager.handFirstCards(dealer);
        ParticipantMoney expected = makePlayerZeroBettingInfo();

        // when
        final ParticipantMoney actual =
                gameManager.getFirstBettingResult(dealer, moneyAfterHand);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    private CardShuffler createBlackCardShuffler() {
        return (card) -> List.of(Card.create(CardPattern.HEART, CardNumber.ACE),
                Card.create(CardPattern.HEART, CardNumber.KING),
                Card.create(CardPattern.SPADE, CardNumber.TWO),
                Card.create(CardPattern.SPADE, CardNumber.THREE),
                Card.create(CardPattern.DIAMOND, CardNumber.FOUR),
                Card.create(CardPattern.CLOVER, CardNumber.FIVE));
    }

    private ParticipantMoney makePlayerZeroBettingInfo() {
        final Map<Participant, BettingMoney> expected = new LinkedHashMap<>();
        expected.put(Player.create("pobi"), BettingMoney.zero());
        expected.put(Player.create("crong"), BettingMoney.zero());
        return ParticipantMoney.create(dealer, expected);
    }
}
