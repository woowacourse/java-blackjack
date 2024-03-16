package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;

import java.util.List;

import static blackjack.utils.Constants.*;

public class OutputView {
    private static final String NEWLINE = System.lineSeparator();
    private static final String ERROR_SUFFIX = "[ERROR]";

    private final MessageGenerator messageGenerator;

    public OutputView(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public void printCardsAfterFirstDeal(ParticipantDto dealer, ParticipantsDto players) {
        printInitialSettingMessage(dealer, players);
        printFirstCardOfDealer(dealer);
        printCardsOfAllPlayer(players);
    }

    private void printInitialSettingMessage(ParticipantDto dealer, ParticipantsDto players) {
        String dealerName = dealer.name();
        String playerNames = messageGenerator.generatePlayerNames(players.participants());

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerName, playerNames, INITIAL_CARD_COUNT);
    }

    private void printFirstCardOfDealer(ParticipantDto dealer) {
        String dealerName = dealer.name();
        List<CardDto> cards = dealer.cards();
        String cardsInfo = messageGenerator.generateCardsInfo(List.of(cards.get(0)));

        System.out.printf("%s: %s%n", dealerName, cardsInfo);
    }

    private void printCardsOfAllPlayer(ParticipantsDto players) {
        List<ParticipantDto> playersDto = players.participants();
        String cardsOfAllPlayer = messageGenerator.generateCardsOfAllParticipant(playersDto);

        System.out.println(cardsOfAllPlayer + NEWLINE);
    }

    public void printCardsOfParticipant(ParticipantDto participant) {
        String cardsOfParticipant = messageGenerator.generateCardsOfParticipant(participant);
        System.out.println(cardsOfParticipant);
    }

    public void printDealerReceiveCard() {
        String message = String.format("%n%s는 %d이하라 한장의 카드를 더 받았습니다.", DEFAULT_NAME_OF_DEALER, DEALER_MIN_SCORE_POLICY);
        System.out.println(message);
    }

    public void printCardsWithScore(ParticipantsDto participantsDto) {
        List<ParticipantDto> participants = participantsDto.participants();
        String cardsWithScore = messageGenerator.generateCardsWithScore(participants);

        System.out.println(NEWLINE + cardsWithScore);
    }

    public void printProfits(ProfitResultDto profitResult) {
        System.out.println(NEWLINE + "## 최종 수익");

        String profits = messageGenerator.generateProfits(profitResult);
        System.out.println(profits);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }
}
