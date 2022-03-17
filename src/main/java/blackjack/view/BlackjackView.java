package blackjack.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.dto.CardDto;
import blackjack.dto.InitiallyDrewCardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;

public class BlackjackView {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Map<String, Integer> requestPlayerBettingAmounts(final List<String> playerNames) {
        final Map<String, Integer> playerBettingAmounts = new HashMap<>();
        for (final String playerName : playerNames) {
            playerBettingAmounts.put(playerName, requestPlayerBettingAmount(playerName));
        }
        return playerBettingAmounts;
    }

    public List<String> requestPlayerNames() {
        outputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputView.requestPlayerNames();
    }

    private int requestPlayerBettingAmount(final String playerName) {
        outputView.printEmptyLine();
        outputView.printMessage(playerName + "의 베팅 금액은?");
        return inputView.requestBettingAmount();
    }

    public void printInitiallyDistributedCards(final InitiallyDrewCardDto dealerInitiallyDrewCardDto,
                                               final List<InitiallyDrewCardDto> playerInitiallyDrewCardDtos) {
        outputView.printEmptyLine();
        outputView.printMessageOfInitiallyDistributeCards(dealerInitiallyDrewCardDto, playerInitiallyDrewCardDtos);
        outputView.printDistributedCardsOfParticipant(dealerInitiallyDrewCardDto);
        playerInitiallyDrewCardDtos.forEach(outputView::printDistributedCardsOfParticipant);
        outputView.printEmptyLine();
    }

    public boolean requestDrawingCardChoice(final String playerName) {
        outputView.printMessageOfRequestDrawingCardChoice(playerName);
        return inputView.requestDrawingCardChoice();
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<CardDto> cardDtos) {
        outputView.printCurrentCardsOfPlayer(playerName, cardDtos);
    }

    public void printMessageOfDealerDrewCard(final String dealerName) {
        outputView.printMessage(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScoresOfParticipants(final ParticipantDto dealerDto, final List<ParticipantDto> playerDtos) {
        outputView.printEmptyLine();
        outputView.printFinalScoreOfParticipant(dealerDto);
        outputView.printFinalScoreOfParticipants(playerDtos);
    }

    public void printMatchResult(final MatchResultDto matchResultDto) {
        outputView.printEmptyLine();
        outputView.printMessage("## 최종 승패");
        outputView.printMatchResult(matchResultDto);
    }

}
