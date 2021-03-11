package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.ScoreResultDto;
import blackjack.dto.WinPrizeDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialCardStatus(Participants participants) {
        System.out.println();
        List<Participant> participantsGroup = participants.getParticipants();
        for (Participant participant : participantsGroup) {
            System.out.println(participant.getName() + ": " + printCards(participant.showInitCards()));
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

    public static void printParticipantCards(Participant participant) {
        System.out.println(participant.getName() + ": " + printCards(participant.showCards()));
    }

    public static void printAllParticipantsCards(Participants participants) {
        System.out.println();
        List<Participant> participantsGroup = participants.getParticipants();
        for (Participant participant : participantsGroup) {
            System.out.println(participant.getName() + ": "
                    + printCards(participant.showCards()) + " - 결과:" + participant.sumTotalScore(new BlackJackScoreRule()));
        }
        System.out.println();
    }


    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printScoreResults(DealerResultDto dealerResultDto, List<ScoreResultDto> scoreResultDtos) {
        System.out.println("## 최종 승패");
        Map<GameResult, Long> results = dealerResultDto.getResult();
        System.out.println(dealerResultDto.getName() + ": " + results.get(GameResult.WIN) + "승 "
                + results.get(GameResult.LOSE) + "패 " + results.get(GameResult.DRAW) + "무");

        for (ScoreResultDto scoreResultDto : scoreResultDtos) {
            System.out.println(scoreResultDto.getName() + ": " + scoreResultDto.getGameResult().getValue());
        }
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
