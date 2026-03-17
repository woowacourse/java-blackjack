package domain;

import domain.model.Card;
import domain.model.Deck;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import domain.service.CardNumberGenerator;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardDistributorTest {

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

    @Test
    void 초기_카드_분배_테스트() {
        // given, when
        Deck initialDeck = cardDistributor.getInitialDeck();

        // then
        assertThat(initialDeck.getSize()).isEqualTo(2);
    }

    @Test
    void 추가_카드_분배_테스트() {
        // given, when
        Card additionalCard = cardDistributor.getAdditionalCard();

        // then
        assertThat(additionalCard).isNotNull();
        assertThat(additionalCard).isInstanceOf(Card.class);
    }
}
