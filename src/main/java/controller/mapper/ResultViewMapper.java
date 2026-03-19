package controller.mapper;

import domain.card.Card;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Players;
import domain.state.Outcome;
import dto.view.DealerStatDto;
import dto.view.ParticipantProfitDto;
import dto.view.ParticipantResultDto;
import dto.view.ParticipantStatsDto;
import dto.view.PlayerCardsDto;
import dto.view.PlayerOutcomeDto;
import dto.view.PlayerProfitDto;
import dto.view.ResultDto;
import dto.view.StartBlackJackDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import message.IOMessage;

public class ResultViewMapper {

    public StartBlackJackDto toStartBlackJackDto(Players players, Dealer dealer) {
        return new StartBlackJackDto(
                formatCardName(dealer.getCards().get(0)),
                toPlayerCardsDtos(players)
        );
    }

    public ResultDto toResultDto(Players players, Dealer dealer) {
        final ParticipantResultDto dealerResult = new ParticipantResultDto(
                IOMessage.DEALER_NAME.message(),
                joinCardNames(dealer.getCards()),
                dealer.getScore()
        );
        final List<ParticipantResultDto> playerResults = new ArrayList<>();
        players.forEachPlayer(player -> playerResults.add(new ParticipantResultDto(
                player.getName(),
                joinCardNames(player.getCards()),
                player.getScore()
        )));
        return new ResultDto(dealerResult, playerResults);
    }

    public ParticipantStatsDto toParticipantStatsDto(Players players, GameResult gameResult) {
        return new ParticipantStatsDto(
                toDealerStatDto(gameResult),
                toPlayerOutcomeDtos(players, gameResult)
        );
    }

    public ParticipantProfitDto toParticipantProfitDto(Players players, int dealerProfit) {
        final List<PlayerProfitDto> playerProfitDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerProfitDtos.add(new PlayerProfitDto(
                player.getName(),
                player.getBalance()
        )));
        return new ParticipantProfitDto(dealerProfit, playerProfitDtos);
    }

    private List<PlayerCardsDto> toPlayerCardsDtos(Players players) {
        final List<PlayerCardsDto> playerCardsDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerCardsDtos.add(new PlayerCardsDto(
                player.getName(),
                toCardNames(player.getCards())
        )));
        return playerCardsDtos;
    }

    private DealerStatDto toDealerStatDto(GameResult gameResult) {
        final int dealerWinCount = gameResult.getDealerCount(Outcome.DEFAULT_WIN)
                + gameResult.getDealerCount(Outcome.BLACKJACK_WIN);
        final int dealerLoseCount = gameResult.getDealerCount(Outcome.LOSE);
        final int dealerDrawCount = gameResult.getDealerCount(Outcome.DRAW);
        return new DealerStatDto(dealerWinCount, dealerLoseCount, dealerDrawCount);
    }

    private List<PlayerOutcomeDto> toPlayerOutcomeDtos(Players players, GameResult gameResult) {
        final List<PlayerOutcomeDto> playerOutcomeDtos = new ArrayList<>();
        players.forEachPlayer(player -> playerOutcomeDtos.add(new PlayerOutcomeDto(
                player.getName(),
                gameResult.getPlayerOutcome(player.getName()).result()
        )));
        return playerOutcomeDtos;
    }

    private List<String> toCardNames(List<Card> cards) {
        return cards.stream()
                .map(this::formatCardName)
                .collect(Collectors.toList());
    }

    private String formatCardName(final Card card) {
        return card.getRank().symbol() + card.getSuit().suit();
    }

    private String joinCardNames(final List<Card> cards) {
        return cards.stream()
                .map(this::formatCardName)
                .collect(Collectors.joining(", "));
    }
}
