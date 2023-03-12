package blackjack.domain;

import blackjack.dto.BettingMoneyDto;
import blackjack.dto.ParticipantsProfitDto;
import blackjack.dto.PersonStatusDto;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(String[] names, Shuffler shuffler) {
        validateNumberOfPlayer(names);
        this.participants = createParticipants(names);
        this.deck = new Deck(shuffler);
    }

    private void validateNumberOfPlayer(String[] names) {
        if (names.length < 1) {
            throw new IllegalArgumentException("[ERROR] 참여할 사람은 한 명 이상이어야 합니다.");
        }
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
        List<Player> players = participants.getPlayers();

        dto.add(PersonStatusDto.of(participants.getDealerName(), participants.getDealerInitCard()));

        for (Person player : players) {
            dto.add(PersonStatusDto.of(player.getName(), player.getInitCards()));
        }

        return dto;
    }

    public List<String> getParticipantsName() {
        return participants.getParticipants().stream()
                .map(Person::getName)
                .collect(toList());
    }

    public List<String> getPlayersName() {
        return participants.getPlayers().stream()
                .map(Person::getName)
                .collect(toList());
    }

    public PersonStatusDto getPlayerStatus(String name) {
        List<Card> cards = participants.getCardsByName(name);
        return PersonStatusDto.of(name, cards);
    }
    public List<PersonStatusDto> getAllPersonStatus() {
        return participants.getParticipants().stream()
                .map(person -> PersonStatusDto.of(person.getName(), person.getCards()))
                .collect(toList());
    }

    public int getDealerScore() {
        return participants.getDealerScore();
    }

    public int getPlayerScore(String name) {
        return participants.getPlayerScore(name);
    }

    public ParticipantsProfitDto getParticipantsProfitDto(BettingMoneyDto bettingMoneyDto) {
        Map<Person, Integer> bettingMoney = bettingMoneyDto.getBettingMoney();
        Exchanger exchanger = new Exchanger(bettingMoney);
        Map<Person, Double> playersProfit = getPlayersProfit(exchanger);
        double dealerProfit = getDealerProfit(exchanger, playersProfit);

        Map<Person, Double> participantsProfit = new HashMap<>(playersProfit);
        participantsProfit.put(participants.getDealer(), dealerProfit);

        return ParticipantsProfitDto.of(participantsProfit);
    }

    private Map<Person, Double> getPlayersProfit(Exchanger exchanger) {
        Person dealer = participants.getDealer();
        List<String> playersName = getPlayersName();
        Map<Person, Double> playersProfit = new HashMap<>();
        for (String name : playersName) {
            Player player = (Player) participants.findByName(name);
            double profit = exchanger.calculatePlayerProfit(player, player.matchGame(dealer));
            playersProfit.put(player, profit);
        }
        return playersProfit;
    }

    private double getDealerProfit(Exchanger exchanger, Map<Person, Double> playersProfit) {
        List<Double> profits = getProfits(playersProfit);
        return exchanger.calculateDealerProfit(profits);
    }

    private List<Double> getProfits(Map<Person, Double> playersProfit) {
        return new ArrayList<>(playersProfit.values());
    }

    public Participants getParticipants() {
        return participants;
    }

    public Person getParticipantByName(String name) {
        return participants.findByName(name);
    }
}
