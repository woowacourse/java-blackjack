package domain.service;

import static constant.BlackJackConstant.MAX_CARD_COUNT;

import domain.model.Card;
import domain.model.CardRank;
import domain.model.CardShape;
import repository.CardRepository;
import util.RandomCardGenerator;

public class CardFactory {

    private final CardRepository cardRepository;
    private final RandomCardGenerator randomCardGenerator;

    public CardFactory(CardRepository cardRepository, RandomCardGenerator randomCardGenerator) {
        this.cardRepository = cardRepository;
        this.randomCardGenerator = randomCardGenerator;
    }

    // 카드 생성 후 저장
    public Card createCard() {
        if (cardRepository.getCardCount() >= MAX_CARD_COUNT) {
            throw new IllegalArgumentException("카드는 52장을 초과할 수 없습니다.");
        }

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
