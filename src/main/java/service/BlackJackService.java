package service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import dto.AllParticipatorsDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardDeck;
import model.Participator;
import model.Participators;
import model.Result;
import util.CardConvertor;
import util.ResultConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Participators participators;
    private CardDeck cardDeck;

    public AllParticipatorsDto initGame(List<String> names) {
        cardDeck = new CardDeck();
        participators = new Participators(names);
        drawFirstTurn();
        return new AllParticipatorsDto(getParticipatorDtos(), convertParticipatorToDto(participators.findDealer()));
    }

    private void drawFirstTurn() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            participators.receiveCardsAll(cardDeck);
        }
    }

    private List<ParticipatorDto> getParticipatorDtos() {
        return participators.findPlayers().stream().map(this::convertParticipatorToDto).collect(toList());
    }

    private ParticipatorDto convertParticipatorToDto(Participator participator) {
        return new ParticipatorDto(participator.getPlayerName(), convertCardsToStrings(participator.getCards()));
    }

    private List<String> convertCardsToStrings(List<Card> cards) {
        return cards.stream()
                .map(CardConvertor::getCardString)
                .collect(toList());
    }

    public List<String> getPlayerNames() {
        return participators.findPlayers().stream()
                .map(Participator::getPlayerName)
                .collect(toList());
    }

    public boolean canReceiveCard(String name) {
        return participators.canReceiveCard(name);
    }

    public ParticipatorDto hitParticipatorOf(String name) {
        participators.receiveCardTo(name, cardDeck);
        return convertParticipatorToDto(participators.findByName(name));
    }

    public Map<String, String> match() {
        Map<String, Result> matchResults = participators.matchAll();
        return convertToPlayerResultsDto(matchResults);
    }

    private Map<String, String> convertToPlayerResultsDto(Map<String, Result> playerMatchResults) {
        return playerMatchResults.entrySet().stream()
                .collect(toMap(entry -> entry.getKey(), entry -> ResultConvertor.convert(entry.getValue())));
    }

    public Map<ParticipatorDto, Integer> getAllCardsResults() {
        participators.findDealer().setEveryCardGettable();
        return participators.findAll().stream()
        .collect(toMap(this::convertParticipatorToDto, Participator::getSum, (participator, sum) -> sum, LinkedHashMap::new));
    }
}
