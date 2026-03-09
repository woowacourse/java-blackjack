package domain;

import domain.model.*;
import domain.service.BlackJackService;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import domain.service.JudgementService;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import repository.PlayerRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackServiceTest {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final DealerRepository dealerRepository = new DealerRepository();
    private final CardRepository cardRepository = new CardRepository();
    private final CardFactory cardFactory = new CardFactory(
            cardRepository,
            new RandomShapeNumberGenerator(),
            new RandomRankNumberGenerator()
    );
    private final CardDistributor cardDistributor = new CardDistributor(
            dealerRepository,
            cardFactory
    );
    private final JudgementService judgementService = new JudgementService(
            playerRepository,
            dealerRepository
    );
    private final BlackJackService blackJackService = new BlackJackService(
            playerRepository,
            dealerRepository,
            cardDistributor,
            judgementService
    );

    @Test
    void 딜러_카드_추가_배부_가능여부_테스트() {

        List<Card> cards = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB)
        );
        Deck deck = Deck.of(cards);
        Dealer dealer = Dealer.of(deck);
        dealerRepository.save(dealer);

        assertThat(blackJackService.isDealerCanAppend()).isFalse();
    }
}
