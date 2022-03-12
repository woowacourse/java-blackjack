package blackjack.view;

import java.util.List;

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

    public List<String> requestPlayerNames() {
        outputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputView.requestPlayerNames();
    }

    public void printInitiallyDistributedCards(final String dealerFirstCardName, final List<ParticipantDto> playerDtos) {
        outputView.printEmptyLine();
        outputView.printMessageOfInitiallyDistributeCards(playerDtos);
        outputView.printFirstCardOfDealer(dealerFirstCardName);
        playerDtos.forEach(outputView::printDistributedCardsOfPlayer);
        outputView.printEmptyLine();
    }

    public boolean requestDrawingCardChoice(final String playerName) {
        outputView.printMessageOfRequestDrawingCardChoice(playerName);
        return inputView.requestDrawingCardChoice();
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<String> cardNames) {
        outputView.printCurrentCardsOfPlayer(playerName, cardNames);
    }

    public void printMessageOfDealerDrewCard() {
        outputView.printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
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
