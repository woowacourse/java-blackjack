package domain;

import org.junit.jupiter.api.Test;
import repository.CardRepository;
import util.NumberGenerator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

public class CardTest {

    class DefaultShapeNumberGenerator implements NumberGenerator {
        @Override
        public int generate() {
            return 1;
        }
    }

    class DefaultRankNumberGenerator implements NumberGenerator {
        @Override
        public int generate() {
            return 2;
        }
    }

    @Test
    void 랜덤_카드_모양_생성_테스트() {
        // given
        NumberGenerator numberGenerator = new DefaultShapeNumberGenerator();

        // when
        CardShape cardShape = CardShape.getShape(numberGenerator.generate());

        // then
        assertThat(cardShape).isEqualTo(CardShape.HEART);
    }

    @Test
    void 랜덤_카드_랭크_생성_테스트() {
        // given
        NumberGenerator numberGenerator = new DefaultRankNumberGenerator();

        // when
        CardRank cardRank = CardRank.getRank(numberGenerator.generate());

        // then
        assertThat(cardRank).isEqualTo(CardRank.TWO);
    }

    @Test
    void 모양과_랭크를_조합하여_카드_생성_테스트() {
        // given
        NumberGenerator shapeNumberGenerator = new DefaultShapeNumberGenerator();
        NumberGenerator rankNumberGenerator = new DefaultRankNumberGenerator();

        CardRank cardRank = CardRank.getRank(rankNumberGenerator.generate());
        CardShape cardShape = CardShape.getShape(shapeNumberGenerator.generate());

        // when
        Card card = Card.of(cardRank, cardShape);

        // then
        assertThat(card.getCardShape()).isEqualTo(CardShape.HEART);
        assertThat(card.getCardRank()).isEqualTo(CardRank.TWO);

    }

    @Test
    void 생성된_카드_중복_검사_성공_테스트() {
        // given
        CardRepository cardRepository = new CardRepository();
        cardRepository.save(Card.of(CardRank.ACE, CardShape.HEART));

        // when
        Card card = Card.of(CardRank.TWO, CardShape.HEART);

        // then
        assertThat(cardRepository.isExist(card)).isEqualTo(false);
    }

    @Test
    void 생성된_카드_중복_검사_실패_테스트() {
        // given
        CardRepository cardRepository = new CardRepository();
        cardRepository.save(Card.of(CardRank.ACE, CardShape.HEART));

        // when
        Card card = Card.of(CardRank.ACE, CardShape.HEART);

        // then
        assertThat(cardRepository.isExist(card)).isEqualTo(true);
    }
}
