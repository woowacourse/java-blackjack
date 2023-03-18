package blackjack.domain;

import blackjack.dto.ParticipantsProfitDto;
import blackjack.dto.PersonStatusDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BlackjackGame {

    private final Deck deck;
    private final BettingMoney bettingMoney;
    private final Participants participants;

    public BlackjackGame(Players players, Shuffler shuffler, BettingMoney bettingMoney) {
        this.participants = createParticipants(players);
        this.deck = new Deck(shuffler);
        this.bettingMoney = bettingMoney;
    }

    public Participants createParticipants(Players players) {
        return new Participants(new Dealer(), players);
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

    public ParticipantsProfitDto getParticipantsProfitDto() {
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
