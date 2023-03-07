package blackjack.domain;

import blackjack.dto.GameResultDto;
import blackjack.dto.PersonStatusDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(String[] names, Shuffler shuffler) {
        this.participants = createParticipants(names);
        this.deck = new Deck(shuffler);
    }

    public Participants createParticipants(String[] names) {
        validateDuplicate(names);
        List<Player> players = Arrays.stream(names)
                .map(Player::new)
                .collect(toList());
        return new Participants(new Dealer(), players);
    }

    private void validateDuplicate(String[] names) {
        long uniqueNamesCount = Arrays.stream(names)
                .distinct()
                .count();
        if (uniqueNamesCount != names.length) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 있습니다.");
        }
    }

    public void drawInitCard() {
        List<Person> persons = participants.getParticipants();
        for (Person person : persons) {
            drawTwoCards(person, deck);
        }
    }

    private void drawTwoCards(Person person, Deck deck) {
        person.addCard(deck.drawCard());
        person.addCard(deck.drawCard());
    }

    public void drawMoreCard(String name) {
        Person player = participants.findByName(name);
        validateOverScore(player);
        player.addCard(deck.drawCard());
    }

    private void validateOverScore(Person person) {
        if (!person.canDrawCard()) {
            throw new IllegalArgumentException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    public void drawDealerMoreCard() {
        Person dealer = participants.getDealer();
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
        }
    }

    public List<PersonStatusDto> getParticipantsInit() {
        List<PersonStatusDto> dto = new ArrayList<>();
        Person dealer = participants.getDealer();
        List<Person> players = participants.getPlayers();

        dto.add(PersonStatusDto.of(dealer.getName(), dealer.getInitCards()));
        for (Person player : players) {
            dto.add(PersonStatusDto.of(player.getName(), player.getInitCards()));
        }
        return dto;
    }

    public List<String> getPlayersName() {
        return participants.getPlayers().stream()
                .map(Person::getName)
                .collect(toList());
    }

    public PersonStatusDto getPlayerStatus(String name) {
        Person player = participants.findByName(name);
        return PersonStatusDto.of(player.getName(), player.getCards());
    }
    public List<PersonStatusDto> getAllPersonStatus() {
        return participants.getParticipants().stream()
                .map(person -> PersonStatusDto.of(person.getName(), person.getCards()))
                .collect(toList());
    }

    public int getDealerScore() {
        return participants.getDealer().getScore();
    }

    public int getPlayerScore(String name) {
        return participants.findByName(name).getScore();
    }

    public GameResultDto getDelearGameResultDto() {
        Person dealer = participants.getDealer();
        List<GameResult> dealerGameResults = participants.getPlayers()
                .stream()
                .map(dealer::matchGame)
                .collect(toList());
        return GameResultDto.of(dealerGameResults);
    }

    public GameResultDto getPlayerGameResultDto(String name) {
        Person dealer = participants.getDealer();
        Person player = participants.findByName(name);
        return GameResultDto.of(player.matchGame(dealer));
    }
}
