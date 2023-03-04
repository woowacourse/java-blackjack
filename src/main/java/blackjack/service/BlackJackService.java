package blackjack.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.People;
import blackjack.domain.Person;
import blackjack.domain.Player;
import blackjack.dto.PersonStatusResponse;
import blackjack.dto.PersonTotalStatusResponse;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalGameResult;
import java.util.List;

public class BlackJackService {
    private static final int START_DRAW_COUNT = 2;

    private final Deck deck;
    private People people;

    public BlackJackService(DeckGenerator deckGenerator) {
        this.deck = deckGenerator.generate();
    }

    public void createPeople(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.drawCards(START_DRAW_COUNT)))
                .collect(toList());
        Dealer dealer = new Dealer(deck.drawCards(START_DRAW_COUNT));
        this.people = new People(dealer, players);
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

    public TotalGameResult getTotalGameResult() {
        Dealer dealer = people.getDealer();
        return people.getPlayers()
                .stream()
                .map(player -> new PlayerGameResult(player.getName(), player.matchGame(dealer)))
                .collect(collectingAndThen(toList(), TotalGameResult::of));
    }
}
