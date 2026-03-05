package domain.service;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import repository.CardRepository;
import util.NumberGenerator;

public class CardFactory {

    private final CardRepository cardRepository;
    private final NumberGenerator randomRankNumberGenerator;
    private final NumberGenerator randomShapeNumberGenerator;
    // TODO: 인스턴스 변수 2개까지 줄이기

    public CardFactory(CardRepository cardRepository, NumberGenerator randomRankNumberGenerator, NumberGenerator randomShapeNumberGenerator) {
        this.cardRepository = cardRepository;
        this.randomRankNumberGenerator = randomRankNumberGenerator;
        this.randomShapeNumberGenerator = randomShapeNumberGenerator;
    }

    // 카드 생성 후 저장
    public Card createCard() {
        Card card = getCard();
        return cardRepository.save(card);
    }

    private Card getCard() {
        CardRank rank = CardRank.getRank(randomRankNumberGenerator.generate());
        CardShape shape = CardShape.getShape(randomShapeNumberGenerator.generate());

        if (!cardRepository.isExistByShapeAndRank(rank, shape)) {
            return Card.of(rank, shape);
        }
        return getCard();
    }
}
