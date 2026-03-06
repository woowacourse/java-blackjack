package domain;

import domain.analyzer.ResultAnalyzer;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.answer.Answer;
import domain.card.CardDeck;
import domain.card.CardGenerator;
import domain.dealer.Dealer;
import domain.player.Player;
import domain.player.PlayerName;
import domain.player.Players;
import domain.player.dto.PlayerHandDto;
import domain.player.dto.PlayerResultDto;
import domain.view.ApplicationView;

import java.util.List;

public class BlackjackGame {

    public ApplicationView view;
    public CardDeck cardDeck;

    public BlackjackGame(ApplicationView view, CardGenerator cardGenerator) {
        this.view = view;
        this.cardDeck = CardDeck.from(cardGenerator);
    }

    public void start() {
        Dealer dealer = enterDealer();
        Players players = enterPlayers();

        dealInitialCard(dealer, players);

        view.printFirstHandOutResult(players.displayNames());
        view.printParticipantHand(PlayerHandDto.of(dealer));
        view.printAllParticipantsHand(getPlayerHandInformation(players));

        players.stream().forEach(player -> {
            drawPlayerCard(player, dealer);
        });

        if (dealer.hitIfRequired()) {
            view.printDealerAdditionalDrawCardMessage();
        }

        // 딜러 + Player의 카드 상황 및 총합 출력
        view.printFinalResultMessage(PlayerResultDto.from(dealer));
        players.stream().forEach(player -> {
            view.printFinalResultMessage(PlayerResultDto.from(player));
        });

        //최종 결과 통계 출력
        ResultAnalysisDto analysis = analyzeBlackjackResult(players, dealer);
        view.printFinalResultMessage(analysis);

    }

    private void drawPlayerCard(Player p, Dealer dealer) {
        while(!p.isBusted()) {
            Answer answer = view.askDrawCard(p.toDisplayMyName());
            if(answer.isNo()) {
                return;
            }

            dealer.handOutCardToPlayer(p, 1);
            view.printParticipantHand(PlayerHandDto.of(p));
        }
    }

    private void dealInitialCard(Dealer dealer, Players players) {
        dealer.drawMySelf(2);
        players.giveMeFirstCardBundle(dealer);
    }

    private Dealer enterDealer() {
        return Dealer.of(cardDeck);
    }

    private Players enterPlayers() {
        return Players.from(requestPlayerNames()
                .stream()
                .map(Player::from)
                .toList()
        );
    }

    private List<PlayerName> requestPlayerNames() {
        return view.requestPlayerNames();
    }

    private List<PlayerHandDto> getPlayerHandInformation(Players players) {
        return players.stream()
                .map(PlayerHandDto::of)
                .toList();
    }

    private ResultAnalysisDto analyzeBlackjackResult(Players players, Dealer dealer) {
        ResultAnalyzer analyzer = getAnalyzer();
        return analyzer.analyze(players, dealer);
    }

    private ResultAnalyzer getAnalyzer() {
        return ResultAnalyzer.getInstance();
    }

}
