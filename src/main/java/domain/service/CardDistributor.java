package domain.service;

import domain.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 분배기 클래스
 * 역할:
 * 딜러에 대한 정보들을 소유하고 있고, 카드 생성기를 이용해서 전달 받은 Player들에게 카드를 분배해주고, 딜러에도 카드를 분배한다.
 */
public class CardDistributor {
    private final CardFactory cardFactory;

    public CardDistributor(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public Deck getInitialDeck() {
        List<Card> cards = new ArrayList<>();
        cards.add(cardFactory.createCard());
        cards.add(cardFactory.createCard());
        return Deck.of(cards);
    }

    public Card getAdditionalCard() {
        return cardFactory.createCard();
    }
}
