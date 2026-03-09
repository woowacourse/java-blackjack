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
    private final PersonService personService;
    private final CardFactory cardFactory;

    public CardDistributor(PersonService personService, CardFactory cardFactory) {
        this.personService = personService;
        this.cardFactory = cardFactory;
    }

    public void initialize(List<Player> players) {
        players.forEach(player -> player.assignDeck(getDeck()));
        Dealer dealer = Dealer.of(getDeck());
        personService.saveAllPlayers(players);
        personService.saveDealer(dealer);
    }

    public void distributeAdditionalCard(Person person) {
        person.appendCard(cardFactory.createCard());
    }

    public Dealer getDealer() {
        return personService.getDealer();
    }

    private Deck getDeck() {
        List<Card> cards = new ArrayList<>();
        cards.add(cardFactory.createCard());
        cards.add(cardFactory.createCard());
        return Deck.of(cards);
    }
}
