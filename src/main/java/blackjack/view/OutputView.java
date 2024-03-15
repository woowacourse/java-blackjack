package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.utils.Constants;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printInitialSettingMessage(final ParticipantDto dealer, final ParticipantsDto players) {
        final String dealerName = dealer.name();
        final List<String> playerNames = getParticipantNames(players.participants());
        final String playerNamesMessage = joinWithComma(playerNames);

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerName, playerNamesMessage, Constants.INITIAL_CARD_COUNT);
    }

    private List<String> getParticipantNames(final List<ParticipantDto> participants) {
        return participants.stream()
                .map(ParticipantDto::name)
                .toList();
    }

    private String joinWithComma(final List<String> source) {
        return String.join(DELIMITER, source);
    }

    public void printFirstCardOfDealer(final ParticipantDto participantDto) {
        final String dealerName = participantDto.name();
        final List<CardDto> cardDtos = participantDto.cards();
        final List<CardDto> firstCardOfDealer = List.of(cardDtos.get(0));
        final String cardsInfoMessage = generateCardsInfoMessage(firstCardOfDealer);

        System.out.printf("%s: %s%n", dealerName, cardsInfoMessage);
    }

    private String generateCardsInfoMessage(final List<CardDto> cards) {
        final List<String> cardsInformation = getCardsInformation(cards);

        return joinWithComma(cardsInformation);
    }

    private List<String> getCardsInformation(final List<CardDto> cards) {
        return cards.stream()
                .map(cardDto -> cardDto.denomination() + cardDto.suit())
                .toList();
    }

    public void printParticipantsCards(final ParticipantsDto participants) {
        final List<ParticipantDto> participantDtos = participants.participants();

        participantDtos.stream()
                .map(this::generateParticipantCardsMessage)
                .forEach(System.out::println);
    }

    private String generateParticipantCardsMessage(ParticipantDto participantDto) {
        final String name = participantDto.name();
        final String cardsInfoMessage = generateCardsInfoMessage(participantDto.cards());

        return String.format("%s카드: %s", name, cardsInfoMessage);
    }
}
