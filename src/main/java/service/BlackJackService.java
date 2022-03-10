package service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import dto.AllParticipatorsDto;
import dto.NamesDto;
import dto.ParticipatorDto;
import dto.TotalResultDto;
import java.util.List;
import java.util.Map;
import model.CardDeck;
import model.Participator;
import model.Participators;
import model.PlayerName;
import model.Result;
import util.CardConvertor;

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
        Map<PlayerName, Result> playerMatchResults = participators.matchAll();
        Map<String, String> playerMatchResultsDto = playerMatchResults.entrySet().stream()
                .collect(toMap(entry -> entry.getKey().getValue(), entry -> entry.getValue().name()));
        List<Result> playersResults = playerMatchResults.values().stream()
                .collect(toList());
        long playerWinCount = countPlayersResults(playersResults, Result.WIN);
        long playerLoseCount = countPlayersResults(playersResults, Result.LOSE);
        long playerDrawCount = countPlayersResults(playersResults, Result.DRAW);
        return new TotalResultDto(playerWinCount, playerLoseCount, playerDrawCount, playerMatchResultsDto);
    }

    private long countPlayersResults(List<Result> playersResults, Result target) {
        return playersResults.stream().filter(result -> result.equals(target)).count();
    }
}
