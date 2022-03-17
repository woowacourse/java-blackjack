package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class InitiallyDrewCardDto {

    private final String participantName;
    private final List<CardDto> cardDtos;

    private InitiallyDrewCardDto(final String participantName, final List<Card> cards) {
        this.participantName = participantName;
        this.cardDtos = cards.stream()
                .map(CardDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static InitiallyDrewCardDto toDto(final Player player) {
        final String playerName = player.getName();
        final List<Card> playerCards = player.getCards();
        return new InitiallyDrewCardDto(playerName, playerCards);
    }

    public static InitiallyDrewCardDto toDto(final Dealer dealer) {
        final String dealerName = dealer.getName();
        final List<Card> dealerCards = List.of(dealer.getFirstCard());
        return new InitiallyDrewCardDto(dealerName, dealerCards);
    }

    public String getParticipantName() {
        return participantName;
    }

    public List<String> getCardNames() {
        return cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

}
