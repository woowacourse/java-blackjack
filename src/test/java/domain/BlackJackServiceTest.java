package domain;

import domain.model.*;
import domain.service.*;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import repository.PlayerBettingRepository;
import repository.PlayerRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackServiceTest {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final DealerRepository dealerRepository = new DealerRepository();
    private final PlayerBettingRepository playerBettingRepository = new PlayerBettingRepository();
    private final PersonService personService = new PersonService(
            playerRepository,
            dealerRepository
    );
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
            personService,
            cardFactory
    );
    private final JudgementService judgementService = new JudgementService(
            personService,
            playerBettingRepository
    );
    private final BlackJackService blackJackService = new BlackJackService(
            personService,
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
