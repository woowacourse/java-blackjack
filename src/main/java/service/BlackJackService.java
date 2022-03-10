package service;

import static java.util.stream.Collectors.toList;

import dto.InitGameDto;
import dto.NamesDto;
import dto.ParticipatorDto;
import java.util.List;
import model.CardDeck;
import model.Participator;
import model.Participators;
import util.CardConvertor;

public class BlackJackService {
    private static final int INIT_CARD_COUNT = 2;

    private Participators participators;
    private CardDeck cardDeck;

    public InitGameDto initGame(NamesDto namesDto) {
        cardDeck = new CardDeck();
        initParticipators(namesDto);
        drawTwoCardsAll();
        return new InitGameDto(getParticipatorDtos(), convertParticipatorToDto(participators.findDealer()));
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

    public ParticipatorDto tryToHit(boolean flag, String name) {
        if (flag) {
            participators.receiveCardTo(name, cardDeck);
        }
        return convertParticipatorToDto(participators.findName(name));
    }

    public boolean canReceiveCard(String name) {
        return participators.canReceiveCard(name);
    }
}
