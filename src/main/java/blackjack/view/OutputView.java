package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.ParticipantPrizeDto;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.ParticipantResultDto;
import blackjack.domain.participant.dealer.DealerFirstCardDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printFirstDrawMessage(List<String> names) {
        String joinedNames = String.join(", ", names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinedNames);
    }

    public void printFirstOpenCards(DealerFirstCardDto dealerFirstOpen, List<ParticipantCardsDto> playersCards) {
        System.out.println(dealerFirstOpen.getName().getValue() + ": " + dealerFirstOpen.getCard());
        playersCards.forEach(this::printPlayerCard);
        System.out.println();
    }

    public void printPlayerCard(ParticipantCardsDto participantCardsDto) {
        System.out.println(
                participantCardsDto.getName().getValue() + "카드: " + parseCards(participantCardsDto));
    }

    private String parseCards(ParticipantCardsDto participantCardsDto) {
        return participantCardsDto.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalResults(ParticipantResultDto dealerResult, List<ParticipantResultDto> playerResults) {
        System.out.println();
        printFinalResult(dealerResult);
        for (ParticipantResultDto result : playerResults) {
            printFinalResult(result);
        }
    }

    private void printFinalResult(ParticipantResultDto result) {
        System.out.println(
                result.getName().getValue() + " 카드: " + result.getCards().stream().map(Card::toString).collect(
                        Collectors.joining(", ")) + " - 결과: " + result.getScore());
    }
    public void printFinalPrize(List<ParticipantPrizeDto> prizes) {
        System.out.println("\n## 최종 수익");
        prizes.forEach(prize -> System.out.printf("%s: %d\n", prize.getParticipantName(), prize.getPrizeAmount()));
    }
}
