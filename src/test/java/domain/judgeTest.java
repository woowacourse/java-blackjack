package domain;

import domain.model.*;
import domain.service.*;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class judgeTest {

    private final PlayerBettings playerBettings = new PlayerBettings();
    private final CardRepository cardRepository = new CardRepository();
    private final CardNumberGenerator cardNumberGenerator = new CardNumberGenerator(
            new RandomRankNumberGenerator(),
            new RandomShapeNumberGenerator()
    );
    private final CardFactory cardFactory = new CardFactory(
            cardRepository,
            cardNumberGenerator
    );
    private final CardDistributor cardDistributor = new CardDistributor(
            cardFactory
    );
    private final Dealer dealer = new Dealer(cardDistributor);
    private final JudgementService judgementService = new JudgementService(playerBettings);

    @Test
    void 플레이어가_버스트일_때_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.NINE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB),
                Card.of(CardRank.EIGHT, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.LOSS);
    }

    // 플레이어 버스트
    @Test
    void 플레이어만_버스트_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.NINE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.LOSS);
    }

    // 딜러 버스트
    @Test
    void 딜러만_버스트_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB),
                Card.of(CardRank.EIGHT, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.WIN);
    }

    // 플레이어 > 딜러
    @Test
    void 플레이어가_딜러보다_합이_21에_가까울_경우_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.FIVE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB),
                Card.of(CardRank.QUEEN, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.WIN);
    }

    // 딜러 > 플레이어
    @Test
    void 딜러가_플레이어보다_합이_21에_가까울_경우_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.FIVE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB),
                Card.of(CardRank.QUEEN, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.LOSS);
    }

    @Test
    void 플레이어와_딜러의_합이_동일한_경우_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.FIVE, CardShape.CLUB),
                Card.of(CardRank.SEVEN, CardShape.DIAMOND)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.THREE, CardShape.HEART),
                Card.of(CardRank.ACE, CardShape.CLUB),
                Card.of(CardRank.TEN, CardShape.DIAMOND)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.DRAW);
    }

    @Test
    void 승패_판단시_최종합으로_계산_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TEN, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.WIN);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭일때_무승부_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.ACE, CardShape.DIAMOND),
                Card.of(CardRank.QUEEN, CardShape.CLUB)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.DRAW);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러가_일반_생존일때_승리_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.ACE, CardShape.DIAMOND),
                Card.of(CardRank.SEVEN, CardShape.CLUB)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.WIN);
    }

    @Test
    void 플레이어가_일반생존이고_딜러가_블랙잭일_때_패배_테스트() {
        // given
        List<Card> cards1 = List.of(
                Card.of(CardRank.THREE, CardShape.HEART),
                Card.of(CardRank.FOUR, CardShape.CLUB)
        );
        Deck playerDeck = Deck.of(cards1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.ACE, CardShape.DIAMOND),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck dealerDeck = Deck.of(cards2);

        // when
        PlayerStatus playerStatus = judgementService.judgementWinning(playerDeck, dealerDeck);

        // then
        assertThat(playerStatus).isEqualTo(PlayerStatus.LOSS);
    }
}
