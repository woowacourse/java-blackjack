package blackjack.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.People;
import blackjack.domain.Person;
import blackjack.domain.Player;
import blackjack.dto.DealerGameResultResponse;
import blackjack.dto.PersonStatusResponse;
import blackjack.dto.PersonTotalStatusResponse;
import blackjack.dto.PlayerGameResultResponse;
import java.util.List;

public class BlackJackService {
    private People people;
    private final Cards deck;

    public BlackJackService(CardsGenerator cardsGenerator) {
        this.deck = cardsGenerator.generate();
    }

    public void createPeople(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(toList());
        this.people = new People(new Dealer(), players);
    }

    public void initDrawCard() {
        for (Person person : people.getPeople()) {
            person.addCard(deck.drawCard());
            person.addCard(deck.drawCard());
        }
    }

    public void drawMoreCardByName(String playerName) {
        Person person = people.findByName(playerName);
        validateOverScore(person);
        person.addCard(deck.drawCard());
    }

    private void validateOverScore(Person person) {
        if (!person.canDrawCard()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    public boolean drawMoreCardForDealer() {
        Person dealer = people.getDealer();
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
            return true;
        }
        return false;
    }

    public List<String> getPlayersName() {
        return people.getPlayers().stream()
                .map(Person::getName)
                .collect(toList());
    }

    public List<PersonStatusResponse> getStartStatusResponse() {
        return people.getPeople().stream()
                .map(PersonStatusResponse::ofStart)
                .collect(toList());
    }

    public List<PersonTotalStatusResponse> getAllPersonTotalResponse() {
        return people.getPeople().stream()
                .map(PersonTotalStatusResponse::of)
                .collect(toList());
    }

    public PersonStatusResponse getPersonStatusResponseByName(String name) {
        Person person = people.findByName(name);
        return PersonStatusResponse.of(person);
    }

    public DealerGameResultResponse getDealerGameResults() {
        Dealer dealer = people.getDealer();
        return people.getPlayers()
                .stream()
                .map(player -> player.matchGame(dealer))
                .map(GameResult::getAntonym)
                .collect(collectingAndThen(toList(), DealerGameResultResponse::new));
    }

    public List<PlayerGameResultResponse> getAllPlayersGameResult() {
        Dealer dealer = people.getDealer();
        return people.getPlayers().stream()
                .map(player -> new PlayerGameResultResponse(player.getName(), player.matchGame(dealer)))
                .collect(toList());
    }
}
