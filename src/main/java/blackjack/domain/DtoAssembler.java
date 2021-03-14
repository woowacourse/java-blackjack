package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DtoAssembler {

    public static ParticipantDto createDealerInitStatusDto(final Dealer dealer) {
        return new ParticipantDto(
            createCardDtos(dealer.getInitCard()),
            dealer.getScoreToInt()
        );
    }

    public static ParticipantDto createDealerStatusDto(Dealer dealer) {
        return new ParticipantDto(
            createCardDtos(dealer.getCards()),
            dealer.getScoreToInt()
        );
    }

    public static List<ParticipantDto> createPlayerStatusDtos(final Players players) {
        return players.toList()
            .stream()
            .map(DtoAssembler::createPlayerStatusDto)
            .collect(Collectors.toList())
            ;
    }

    public static ParticipantDto createPlayerStatusDto(final Player player) {
        return new ParticipantDto(
            player.getName(),
            createCardDtos(player.getCards()),
            player.getScoreToInt()
        );
    }

    private static List<CardDto> createCardDtos(final List<Card> cards) {
        return cards.stream()
            .map(card -> new CardDto(card.getNumberName() + card.getPatternName()))
            .collect(Collectors.toList())
            ;
    }

    public static ResultDto createDealerResultDto(final List<BetAmount> betAmounts) {
        return new ResultDto(getSumOfAllPlayerProfits(betAmounts) * -1);
    }

    private static double getSumOfAllPlayerProfits(final List<BetAmount> betAmounts) {
        return betAmounts.stream()
            .mapToDouble(BetAmount::getValue)
            .sum();
    }

    public static List<ResultDto> createPlayerResultDtos(final Players players,
        final List<BetAmount> betAmounts) {
        Iterator<String> playersIterator = getPlayersIterator(players);
        Iterator<Double> betAmountIterator = getBetAmountsIterator(betAmounts);
        List<ResultDto> resultDtos = new ArrayList<>();
        while (playersIterator.hasNext()) {
            resultDtos.add(new ResultDto(playersIterator.next(), betAmountIterator.next()));
        }
        return resultDtos;
    }

    private static Iterator<String> getPlayersIterator(final Players players) {
        return players.toList()
            .stream()
            .map(Player::getName)
            .iterator();
    }

    private static Iterator<Double> getBetAmountsIterator(final List<BetAmount> betAmounts) {
        return betAmounts.stream()
            .map(BetAmount::getValue)
            .iterator();
    }
}
