package service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import dto.AllParticipatorsDto;
import dto.NamesDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.CardDeck;
import model.Participator;
import model.Participators;
import model.PlayerName;
import model.Result;
import util.CardConvertor;
import util.ResultConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Participators participators;
    private CardDeck cardDeck;

    public AllParticipatorsDto initGame(NamesDto namesDto) {
        cardDeck = new CardDeck();
        initParticipators(namesDto);
        drawTwoCardsAll();
        return new AllParticipatorsDto(getParticipatorDtos(), convertParticipatorToDto(participators.findDealer()));
    }

    private List<ParticipatorDto> getParticipatorDtos() {
        return participators.findPlayers().stream().map(this::convertParticipatorToDto).collect(toList());
    }

    private ParticipatorDto convertParticipatorToDto(Participator participator) {
        return new ParticipatorDto(getParticipatorNameString(participator), getParticipatorCardsString(participator));
    }

    private String getParticipatorNameString(Participator participator) {
        return participator.getPlayerName().getValue();
    }

    private List<String> getParticipatorCardsString(Participator participator) {
        return participator.getCards().stream()
                .map(CardConvertor::getCardString)
                .collect(toList());
    }

    private void initParticipators(NamesDto namesDto) {
        participators = new Participators(namesDto.getNames());
    }

    private void drawTwoCardsAll() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            participators.receiveCards(cardDeck);
        }
    }

    public List<String> getPlayerNames() {
        return participators.getPlayerNames();
    }

    public ParticipatorDto tryToHit(boolean canHit, String name) {
        if (canHit) {
            participators.receiveCardTo(name, cardDeck);
        }
        return convertParticipatorToDto(participators.findName(name));
    }

    public boolean canReceiveCard(String name) {
        return participators.canReceiveCard(name);
    }

    public ParticipatorDto tryToHitForDealer() {
        return convertParticipatorToDto(participators.tryToHitForDealer(cardDeck));
    }

    public TotalResultDto match() {
        Map<PlayerName, Result> playersFinalMatchResult = participators.getPlayersFinalMatchResult();
        Map<PlayerName, List<Result>> dealerFinalMatchResult = participators.getDealerFinalMatchResult();

        Map<String, String> parsedPlayersFinalMatchResult = parsePlayersFinalMatchResult(playersFinalMatchResult);
        Map<String, List<String>> parsedDealerFinalMatchResult = parseDealerFinalMatchResult(dealerFinalMatchResult);

        return new TotalResultDto(parsedDealerFinalMatchResult, parsedPlayersFinalMatchResult);
    }

    private Map<String, List<String>> parseDealerFinalMatchResult(
            Map<PlayerName, List<Result>> dealerFinalMatchResult) {
        return dealerFinalMatchResult.entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().getValue(), entry -> ResultConvertor.convert(entry.getValue())));

    }

    private Map<String, String> parsePlayersFinalMatchResult(Map<PlayerName, Result> playersFinalMatchResult) {
        return playersFinalMatchResult.entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey().getValue(),
                        entry -> ResultConvertor.convert(entry.getValue()),
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    public Map<ParticipatorDto, Integer> getResult() {
        return participators.findAll().stream()
                .collect(toMap(this::convertParticipatorToDto, Participator::getSum, (x, y) -> y, LinkedHashMap::new));
    }
}
