package domain;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import domain.model.Dealer;
import domain.model.Deck;
import domain.model.Player;
import domain.model.PlayerStatus;
import domain.service.JudgementService;
import java.util.List;
import org.junit.jupiter.api.Test;
import repository.DealerRepository;
import repository.PlayerRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class judgementServiceTest {

    private JudgementService judgementService = new JudgementService(
            new PlayerRepository(),
            new DealerRepository()
    );

    @Test
    void 딜러보다_플레이어의_합이_크면_플레이어_승() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> playerCards = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.ACE, CardShape.HEART)
        );

        List<Card> dealerCards = List.of(
                Card.of(CardRank.FOUR, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB)
        );

        phobi.assignDeck(Deck.of(playerCards));
        Dealer dealer = Dealer.of(Deck.of(dealerCards));

        // when
        judgementService.judgementWinning(phobi, dealer);

        // then
        assertThat(phobi.getPlayerStatus()).isEqualTo(PlayerStatus.WIN);
    }

    @Test
    void 딜러보다_플레이어의_합이_작으면_플레이어_패() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> playerCards = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.SEVEN, CardShape.HEART)
        );

        List<Card> dealerCards = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB)
        );

        phobi.assignDeck(Deck.of(playerCards));
        Dealer dealer = Dealer.of(Deck.of(dealerCards));

        // when
        judgementService.judgementWinning(phobi, dealer);

        // then
        assertThat(phobi.getPlayerStatus()).isEqualTo(PlayerStatus.LOSS);
    }

    @Test
    void 플레이어가_버스트면_플레이어_패() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> playerCards = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.SEVEN, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );

        List<Card> dealerCards = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB)
        );

        phobi.assignDeck(Deck.of(playerCards));
        Dealer dealer = Dealer.of(Deck.of(dealerCards));

        // when
        judgementService.judgementWinning(phobi, dealer);

        // then
        assertThat(phobi.getPlayerStatus()).isEqualTo(PlayerStatus.LOSS);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_버스트_아니면_플레이어_승() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> playerCards = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.SEVEN, CardShape.HEART)
        );

        List<Card> dealerCards = List.of(
                Card.of(CardRank.SEVEN, CardShape.SPADE),
                Card.of(CardRank.EIGHT, CardShape.CLUB),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );

        phobi.assignDeck(Deck.of(playerCards));
        Dealer dealer = Dealer.of(Deck.of(dealerCards));

        // when
        judgementService.judgementWinning(phobi, dealer);

        // then
        assertThat(phobi.getPlayerStatus()).isEqualTo(PlayerStatus.WIN);
    }

    @Test
    void 합계가_같으면_무승부() {
        // given
        Player phobi = Player.of("phobi");

        List<Card> playerCards = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.SEVEN, CardShape.DIAMOND)
        );

        List<Card> dealerCards = List.of(
                Card.of(CardRank.SEVEN, CardShape.SPADE),
                Card.of(CardRank.EIGHT, CardShape.CLUB)
        );

        phobi.assignDeck(Deck.of(playerCards));
        Dealer dealer = Dealer.of(Deck.of(dealerCards));

        // when
        judgementService.judgementWinning(phobi, dealer);

        // then
        assertThat(phobi.getPlayerStatus()).isEqualTo(PlayerStatus.DRAW);
    }
}
