package domain.service;

import domain.model.*;
import repository.DealerRepository;

import java.util.List;

/**
 * 카드 분배기 클래스
 * 역할:
 * 딜러에 대한 정보들을 소유하고 있고, 카드 생성기를 이용해서 전달 받은 Player들에게 카드를 분배해주고, 딜러에도 카드를 분배한다.
 *
 */
public class CardDistributor {

    private final DealerRepository dealerRepository;
    private final CardFactory cardFactory;

    public CardDistributor(DealerRepository dealerRepository, CardFactory cardFactory) {
        this.dealerRepository = dealerRepository;
        this.cardFactory = cardFactory;
    }

    public void initialize(List<Player> players) {
        // player와 Dealer에 생성된 카드 부여
        // 카드 두개씩 생성하고 Deck 생성하고 Player와 Dealer에 부여
        for (Player player : players) {
            Deck deck = Deck.of(List.of(cardFactory.createCard(), cardFactory.createCard()));
            player.assignDeck(deck);
        }

        Dealer dealer = Dealer.of(Deck.of(List.of(cardFactory.createCard(), cardFactory.createCard())));
        dealerRepository.save(dealer);
    }

    public Dealer getDealer() {
        return dealerRepository.getDealer();
    }
}
