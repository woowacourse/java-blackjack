package blackjack.view;

import blackjack.domain.dealer.Dealer;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.dto.PlayerWinningResultDto;
import blackjack.dto.WinningCountDto;
import blackjack.dto.WinningResultDto;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OutputView {

    public void printStartCards(final List<ParticipantCardsDto> participantCardsDtos) {
        final List<ParticipantCardsDto> playersCard = getExceptDealer(participantCardsDtos,
                participantCardsDto -> participantCardsDto.name().equals(Dealer.DEALER_NAME));

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", Dealer.DEALER_NAME,
                String.join(", ", convertToNames(playersCard)));

        printDealerCardScore(getOnlyDealer(participantCardsDtos,
                participantCardsDto -> participantCardsDto.name().equals(Dealer.DEALER_NAME)));
        playersCard.forEach(this::printPlayerCard);
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

    public void printFinalResult(final List<ParticipantScoreDto> participantScoreDtos,
                                 final WinningResultDto winningResultDto) {
        printCardScore(participantScoreDtos);
        printWinningResult(winningResultDto);
    }

    private void printCardScore(final List<ParticipantScoreDto> participantScoreDtos) {
        printDealerCardScore(getOnlyDealer(participantScoreDtos,
                participantCardsDto -> participantCardsDto.participantCards().name()
                        .equals(Dealer.DEALER_NAME)));
        printPlayersCardScore(getExceptDealer(participantScoreDtos,
                playersCardScoreDto -> playersCardScoreDto.participantCards().name()
                        .equals(Dealer.DEALER_NAME)));
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

    private void printWinningResult(final WinningResultDto winningResultDto) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinningResult(winningResultDto.dealerWinningResult());
        printPlayersWinningResult(winningResultDto.playersWinningResult());
    }

    private void printDealerWinningResult(final List<WinningCountDto> dealerWinningResult) {
        System.out.printf("%s: %s%n", Dealer.DEALER_NAME, convertToWinningResult(dealerWinningResult));

    }

    private String convertToWinningResult(final List<WinningCountDto> winningResult) {
        return winningResult.stream()
                .map(winningCountDto -> winningCountDto.count() + winningCountDto.winStatus())
                .collect(Collectors.joining(" "));
    }

    private void printPlayersWinningResult(final List<PlayerWinningResultDto> playersWinningResult) {
        playersWinningResult.forEach(playerWinningResultDto ->
                System.out.printf("%s: %s%n", playerWinningResultDto.name(), playerWinningResultDto.winStatus()));

    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }

    private <T> List<T> getExceptDealer(final List<T> resultDtos, Function<T, String> getName) {
        return resultDtos.stream()
                .filter(item -> !getName.apply(item).equals(DealerFormat.DEALER.getSignal()))
                .toList();
    }

    private <T> T getOnlyDealer(final List<T> resultDtos, Function<T, String> getName) {
        return resultDtos.stream()
                .filter(item -> getName.apply(item).equals(DealerFormat.DEALER.getSignal()))
                .findAny()
                .orElseThrow();
    }
}

