package service;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static model.Dealer.DEALER_NAME;

import dto.AllCardsAndSumDto;
import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import model.Dealer;
import model.Participator;
import model.Players;
import model.Result;
import model.card.Card;
import model.card.CardDeck;
import util.CardConvertor;
import util.ResultConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    public AllParticipatorsDto initGame(final List<String> names) {
        initParticipatorsAndCardDeck(names);
        drawFirstTurn();
        return new AllParticipatorsDto(getPlayersDto(), toParticipatorDto(dealer));
    }

    private void initParticipatorsAndCardDeck(List<String> names) {
        cardDeck = new CardDeck();
        players = Players.of(names);
        dealer = new Dealer();
    }

    private void drawFirstTurn() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            players.receiveCardsAll(cardDeck);
            dealer.receiveCard(cardDeck.drawCard());
        }
    }

    private List<ParticipatorDto> getPlayersDto() {
        return players.getPlayers().stream()
                .map(this::toParticipatorDto)
                .collect(toList());
    }

    private ParticipatorDto toParticipatorDto(Participator participator) {
        return new ParticipatorDto(participator.getPlayerName(), toCardsDto(participator.getCards()));
    }

    private List<String> toCardsDto(List<Card> cards) {
        return cards.stream()
                .map(CardConvertor::getCardString)
                .collect(toList());
    }

    public List<String> getPlayerNames() {
        return players.getPlayers().stream()
                .map(Participator::getPlayerName)
                .collect(toList());
    }

    public boolean canReceiveCard(String name) {
        if (name.equals(DEALER_NAME)) {
            return dealer.canReceiveCard();
        }
        return players.canReceiveCard(name);
    }

    public ParticipatorDto hitParticipatorOf(String name) {
        players.receiveCardTo(name, cardDeck);
        return toParticipatorDto(players.findByName(name));
    }

    public ParticipatorDto hitDealer() {
        dealer.receiveCard(cardDeck.drawCard());
        return toParticipatorDto(dealer);
    }

    public TotalResultDto match() {
        Map<String, Result> playerResults = players.matchWith(dealer);
        return new TotalResultDto(toPlayerResultDto(playerResults), toDealerResultDto(playerResults));
    }

    private Map<String, String> toPlayerResultDto(Map<String, Result> playerMatchResults) {
        return playerMatchResults.entrySet().stream()
                .collect(toMap(Entry::getKey, entry -> ResultConvertor.convert(entry.getValue())));
    }

    private Map<String, Long> toDealerResultDto(Map<String, Result> playerResults) {
        return playerResults.values().stream()
                .map(result -> ResultConvertor.convert(result.getOpposite()))
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()));
    }

    public AllCardsAndSumDto getAllCardsAndSums() {
        dealer.setEveryCardGettable();
        return new AllCardsAndSumDto(getPlayerCardsAndSum(), toParticipatorDto(dealer), dealer.getSum());
    }

    private LinkedHashMap<ParticipatorDto, Integer> getPlayerCardsAndSum() {
        return players.getPlayers().stream().collect(
                toMap(this::toParticipatorDto, Participator::getSum, (participator, sum) -> sum, LinkedHashMap::new));
    }
}
