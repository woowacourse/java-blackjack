package domain.service;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import repository.CardRepository;
import util.RandomCardGenerator;

public class CardFactory {

    private final CardRepository cardRepository;
    private final RandomCardGenerator randomCardGenerator;
    // TODO: 인스턴스 변수 2개까지 줄이기

    public CardFactory(CardRepository cardRepository, RandomCardGenerator randomCardGenerator) {
        this.cardRepository = cardRepository;
        this.randomCardGenerator = randomCardGenerator;
    }

    // 카드 생성 후 저장
    public Card createCard() {
        Card card = getCard();
        return cardRepository.save(card);
    }

    private Card getCard() {
        CardRank rank = randomCardGenerator.generateRank();
        CardShape shape = randomCardGenerator.generateShape();

        if (!cardRepository.isExistByShapeAndRank(rank, shape)) {
            return Card.of(rank, shape);
        }
        return getCard();
    }
}
