package blackjack.view;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.dto.BettingResultDto;
import blackjack.dto.BettingResultDtos;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.ParticipantScoresDto;
import java.util.List;
import java.util.function.Predicate;

public class OutputView {

    public void printStartCards(final List<ParticipantCardsDto> participantCardsDtos) {
        final List<ParticipantCardsDto> playersCard = getExceptDealer(participantCardsDtos,
                participantCardsDto -> participantCardsDto.name().equals(Dealer.DEALER_NAME));

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", Dealer.DEALER_NAME, String.join(", ", convertToNames(playersCard)));

        printDealerCardScore(getOnlyDealer(participantCardsDtos, participantCardsDto -> participantCardsDto.name().equals(Dealer.DEALER_NAME)));
        playersCard.forEach(this::printPlayerCard);
        System.out.println();
    }

    private List<String> convertToNames(final List<ParticipantCardsDto> playersCard) {
        return playersCard.stream()
                .map(ParticipantCardsDto::name)
                .toList();
    }

    public void printDealerCardScore(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s카드: %s%n", Dealer.DEALER_NAME,
                convertToCardsFormat(participantCardsDto.cardDtos()));
    }

    public void printPlayerCard(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s카드: %s%n", participantCardsDto.name(),
                convertToCardsFormat(participantCardsDto.cardDtos()));
    }

    private String convertToCardsFormat(final List<CardDto> cards) {
        return String.join(", ",
                cards.stream()
                        .map(cardDto -> cardDto.number() + cardDto.shape())
                        .toList());
    }

    public void printDealerMoreCard(int count) {
        while (count-- > 0) {
            System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_NAME);
        }
    }

    public void printFinalResult(final ParticipantScoresDto participantScoreDtos,
                                 final BettingResultDtos bettingResultDtos) {
        printCardScore(participantScoreDtos);
        printBettingResult(bettingResultDtos);
    }

    private void printCardScore(final ParticipantScoresDto participantScoreDtos) {
        List<ParticipantScoreDto> participantScores = participantScoreDtos.participantScores();

        printDealerCardScore(getOnlyDealer(participantScores,
                participantScore -> participantScore.participantCards().name().equals(Dealer.DEALER_NAME)));

        printPlayersCardScore(getExceptDealer(participantScores,
                participantScore -> participantScore.participantCards().name().equals(Dealer.DEALER_NAME)));
        System.out.println();
    }

    private void printDealerCardScore(final ParticipantScoreDto dealerCardScore) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n",
                Dealer.DEALER_NAME,
                convertToCardsFormat(dealerCardScore.participantCards().cardDtos()),
                dealerCardScore.score());
    }

    private void printPlayersCardScore(final List<ParticipantScoreDto> playersCardScores) {
        playersCardScores.forEach(this::printPlayerCardScore);
    }

    private void printPlayerCardScore(ParticipantScoreDto playersCardScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n",
                playersCardScore.participantCards().name(),
                convertToCardsFormat(playersCardScore.participantCards().cardDtos()),
                playersCardScore.score());
    }

    private void printBettingResult(final BettingResultDtos rawBettingResultDtos) {
        System.out.println("## 최종 수익");

        List<BettingResultDto> bettingResultDtos = rawBettingResultDtos.bettingResultDtos();
        printDealerBettingResult(getOnlyDealer(bettingResultDtos, bettingResultDto -> bettingResultDto.name().equals(Dealer.DEALER_NAME)));
        printPlayerBettingResults(getExceptDealer(bettingResultDtos, bettingResultDto -> bettingResultDto.name().equals(Dealer.DEALER_NAME)));
    }

    private void printDealerBettingResult(final BettingResultDto dealerBettingResult) {
        System.out.printf("%s: %d%n", dealerBettingResult.name(), dealerBettingResult.betting());

    }

    private void printPlayerBettingResults(final List<BettingResultDto> playerBettingResults) {
        playerBettingResults.forEach(playerBettingResult ->
                System.out.printf("%s: %d%n", playerBettingResult.name(), playerBettingResult.betting()));

    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }

    private <T> List<T> getExceptDealer(final List<T> resultDtos, Predicate<T> matchName) {
        return resultDtos.stream()
                .filter(resultDto -> !matchName.test(resultDto))
                .toList();
    }

    private <T> T getOnlyDealer(final List<T> resultDtos, Predicate<T> matchName) {
        return resultDtos.stream()
                .filter(matchName)
                .findAny()
                .orElseThrow();
    }
}

