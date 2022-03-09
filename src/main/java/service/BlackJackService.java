package service;

import java.util.ArrayList;
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
import util.CardConvertor;

public class BlackJackService {
    private List<Participator> participators;
    private CardDeck cardDeck;

    public InitGameDto initGame(NamesDto namesDto) {
        cardDeck = new CardDeck();
        initParticipators(namesDto);
        drawTwoCardsAll();
        return new InitGameDto(getParticpatorsNames(), getParticipatorsCards());
    }

    private List<String> getParticpatorsNames() {
        return participators.stream()
                .map(Participator::getPlayerName)
                .map(PlayerName::getValue)
                .collect(Collectors.toList());
    }

    private List<List<String>> getParticipatorsCards() {
        return participators.stream()
                .map(participator -> getCardsToDto(participator.getCards()))
                .collect(Collectors.toList());
    }

    private List<String> getCardsToDto(List<Card> cards) {
        return cards.stream()
                .map(CardConvertor::getCardString)
                .collect(Collectors.toList());
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
