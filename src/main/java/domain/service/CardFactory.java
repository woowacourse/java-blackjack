package domain.service;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import repository.CardRepository;

public class CardFactory {

    private final CardRepository cardRepository;
    private final CardNumberGenerator cardNumberGenerator;
    // TODO: 인스턴스 변수 2개까지 줄이기

    public CardFactory(CardRepository cardRepository, CardNumberGenerator cardNumberGenerator) {
        this.cardRepository = cardRepository;
        this.cardNumberGenerator = cardNumberGenerator;
    }

    // 카드 생성 후 저장
    public Card createCard() {
        Card card = getCard();
        return cardRepository.save(card);
    }

    private Card getCard() {
        CardRank rank = cardNumberGenerator.generateRank();
        CardShape shape = cardNumberGenerator.generateShape();
        if (!cardRepository.isExistByShapeAndRank(rank, shape)) {
            return Card.of(rank, shape);
        }
        return getCard();
    }
}
