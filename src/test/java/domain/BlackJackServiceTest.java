package domain;

import domain.model.*;
import domain.service.*;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackServiceTest {

    private final Players players = new Players();
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
    private final BlackJackService blackJackService = new BlackJackService(
            judgementService,
            players,
            playerBettings,
            dealer
    );

    @Test
    void 딜러_카드_추가_배부_가능여부_테스트() {

        List<Card> cards = List.of(
                Card.of(CardRank.ACE, CardShape.HEART),
                Card.of(CardRank.SIX, CardShape.CLUB)
        );
        Deck deck = Deck.of(cards);
        dealer.assignDeck(deck);

        assertThat(blackJackService.isDealerCanAppend()).isFalse();
    }
}
