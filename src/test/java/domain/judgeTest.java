package domain;

import domain.model.*;
import domain.service.JudgementService;
import domain.service.PersonService;
import org.junit.jupiter.api.Test;
import repository.DealerRepository;
import repository.PlayerBettingRepository;
import repository.PlayerRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class judgeTest {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final DealerRepository dealerRepository = new DealerRepository();
    private final PlayerBettingRepository playerBettingRepository = new PlayerBettingRepository();
    private final PersonService personService = new PersonService(
            playerRepository,
            dealerRepository
    );
    private final JudgementService judgementService = new JudgementService(
            personService,
            playerBettingRepository
    );

    @Test
    void 플레이어_딜러_둘다_버스트_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.NINE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB),
                Card.of(CardRank.EIGHT, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.LOSS);
    }

    // 플레이어 버스트
    @Test
    void 플레이어만_버스트_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.NINE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB),
                Card.of(CardRank.NINE, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.LOSS);
    }

    // 딜러 버스트
    @Test
    void 딜러만_버스트_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.EIGHT, CardShape.HEART),
                Card.of(CardRank.EIGHT, CardShape.CLUB),
                Card.of(CardRank.EIGHT, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.WIN);
    }

    // 플레이어 > 딜러
    @Test
    void 플레이어가_딜러보다_합이_21에_가까울_경우_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.FIVE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB),
                Card.of(CardRank.QUEEN, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.WIN);
    }

    // 딜러 > 플레이어
    @Test
    void 딜러가_플레이어보다_합이_21에_가까울_경우_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.FIVE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB),
                Card.of(CardRank.QUEEN, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.LOSS);
    }

    // 플레이어 == 딜러
    @Test
    void 플레이어와_딜러의_합이_동일한_경우_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.FIVE, CardShape.CLUB),
                Card.of(CardRank.SEVEN, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.THREE, CardShape.HEART),
                Card.of(CardRank.ACE, CardShape.CLUB),
                Card.of(CardRank.TEN, CardShape.DIAMOND)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.DRAW);
    }

    @Test
    void 승패_판단시_최종합으로_계산_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.TEN, CardShape.HEART),
                Card.of(CardRank.NINE, CardShape.CLUB)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.WIN);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭일때_무승부_테스트() {
        // given
        Player player = Player.of("phobi");
        playerRepository.save(player);
        List<Card> cards1 = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.TEN, CardShape.CLUB)
        );
        Deck deck1 = Deck.of(cards1);
        player.assignDeck(deck1);

        List<Card> cards2 = List.of(
                Card.of(CardRank.ACE, CardShape.DIAMOND),
                Card.of(CardRank.QUEEN, CardShape.CLUB)
        );
        Deck deck2 = Deck.of(cards2);
        Dealer dealer = Dealer.of(deck2);
        dealerRepository.save(dealer);

        // when
        judgementService.judgementWinning(player, dealer);

        // then
        assertThat(player.getPlayerStatus()).isEqualTo(PlayerStatus.DRAW);
    }
}
