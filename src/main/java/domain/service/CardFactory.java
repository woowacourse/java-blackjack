package domain.service;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import repository.CardRepository;

import static constant.ErrorMessage.EXCEEDED_MAX_TRY;

public class CardFactory {

    private final CardRepository cardRepository;
    private final CardNumberGenerator cardNumberGenerator;

    private final int MAX_TRY = 5;

    public CardFactory(CardRepository cardRepository, CardNumberGenerator cardNumberGenerator) {
        this.cardRepository = cardRepository;
        this.cardNumberGenerator = cardNumberGenerator;
    }

    public Card createCard() {
        Card card = getCard();
        return cardRepository.save(card);
    }

    private Card getCard() {
        for (int i = 0; i < MAX_TRY; i++) {
            CardRank rank = cardNumberGenerator.generateRank();
            CardShape shape = cardNumberGenerator.generateShape();
            if (!cardRepository.isExistByShapeAndRank(rank, shape)) {
                return Card.of(rank, shape);
            }
        }
        throw new IllegalArgumentException(EXCEEDED_MAX_TRY.getMessage());
    }
}
