package domain;

import domain.model.*;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import domain.service.CardNumberGenerator;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonTest {

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

    @Test
    void 블랙잭이_아니고_승일때_수익률_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TWO, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);
        player.changeStatus(PlayerStatus.WIN);

        // when
        player.applyBetting(10_000);

        // then
        assertThat(player.getProfit()).isEqualTo(10_000);
    }

    @Test
    void 블랙잭이_아니고_합이_21이고_승일때_수익률_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.QUEEN, CardShape.HEART),
                Card.of(CardRank.FIVE, CardShape.CLUB),
                Card.of(CardRank.SIX, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);
        player.changeStatus(PlayerStatus.WIN);

        // when
        player.applyBetting(10_000);

        // then
        assertThat(player.getProfit()).isEqualTo(10_000);
    }

    @Test
    void 블랙잭이고_승일때_수익률_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);
        player.changeStatus(PlayerStatus.WIN);

        // when
        player.applyBetting(10_000);

        // then
        assertThat(player.getProfit()).isEqualTo(15_000);
    }

    @Test
    void 패일때_수익률_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TWO, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);
        player.changeStatus(PlayerStatus.LOSS);

        // when
        player.applyBetting(10_000);

        // then
        assertThat(player.getProfit()).isEqualTo(-10_000);
    }

    @Test
    void 무일때_수익률_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TWO, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);
        player.changeStatus(PlayerStatus.DRAW);

        // when
        player.applyBetting(10_000);

        // then
        assertThat(player.getProfit()).isEqualTo(0);
    }

    @Test
    void 딜러_카드_추가_여부_테스트() {
        //given
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck = Deck.of(cards1);
        dealer.assignDeck(deck);

        // when
        boolean canAppend = dealer.canAppend();

        // then
        assertThat(canAppend).isFalse();
    }

    @Test
    void 플레이어_카드_추가_여부_테스트() {
        //given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        // when
        boolean canAppend = player.canAppend();

        // then
        assertThat(canAppend).isFalse();
    }
}
