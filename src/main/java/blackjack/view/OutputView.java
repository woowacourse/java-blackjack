package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.AbstractParticipant;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.dto.GameResultDto;
import blackjack.dto.WinPrizeDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static String MSG_DEALER_GET_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialCardStatus(Participants participants) {
        System.out.println();
        List<AbstractParticipant> participantsGroup = participants.getParticipants();
        for (AbstractParticipant participant : participantsGroup) {
            System.out.println(participant.getName() + ": " + printCards(participant.showCards()));
        }
        System.out.println();
    }

    private static String printCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(", "));
    }

    private static String printCard(Card card) {
        return card.getCardValue().getValue() + "" + card.getCardType().getValue();
    }

    public static void printParticipantCards(AbstractParticipant participant) {
        System.out.println(participant.getName() + ": " + printCards(participant.showCards()));
    }

    public static void printAllParticipantsCards(Participants participants) {
        System.out.println();
        List<AbstractParticipant> participantsGroup = participants.getParticipants();
        for (AbstractParticipant participant : participantsGroup) {
            System.out.println(participant.getName() + ": "
                    + printCards(participant.showCards()) + " - 결과:" + participant.sumTotalScore(new BlackJackScoreRule()));
        }
        System.out.println();
    }

    public static void printWinPrizeResult(GameResultDto gameResultDto) {
        System.out.println("## 최종 수익");
        WinPrizeDto dealerWinPrizeDto = gameResultDto.getDealerWinPrize();
        System.out.println(dealerWinPrizeDto.getName() + " : " + dealerWinPrizeDto.getMoney());
        for (WinPrizeDto winPrizeDto : gameResultDto.getPlayersWinPrize()) {
            System.out.println(winPrizeDto.getName() + " : " + winPrizeDto.getMoney());
        }
    }
}
