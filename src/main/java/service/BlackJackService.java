package service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import model.Card;
import model.Cards;
import service.dto.InitGameDto;
import service.dto.NamesDto;
import java.util.List;
import java.util.stream.Collectors;
import model.CardDeck;
import model.Dealer;
import model.Participator;
import model.Player;
import model.PlayerName;
import service.dto.ParticipatorDto;
import util.CardConvertor;

public class BlackJackService {
    private List<Participator> participators;
    private CardDeck cardDeck;

    public InitGameDto initGame(NamesDto namesDto) {
        cardDeck = new CardDeck();
        initParticipators(namesDto);
        drawTwoCardsAll();
        return new InitGameDto(getParticipatorDtos());
    }

    private List<ParticipatorDto> getParticipatorDtos() {
        return participators.stream()
                .map(this::convertParticipatorToDto)
                .collect(toList());
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
        participators = new ArrayList<>();
        participators.add(new Dealer());
        for (String name : namesDto.getNames()) {
            participators.add(new Player(new PlayerName(name)));
        }
    }

    private void drawTwoCardsAll() {
        for (Participator participator : participators) {
            for (int i = 0 ; i < 2 ; i++) {
                participator.receiveCard(cardDeck.drawCard());
            }
        }
    }
}
