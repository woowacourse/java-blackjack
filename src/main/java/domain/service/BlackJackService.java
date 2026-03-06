package domain.service;

import domain.model.Player;
import dto.CardDto;
import dto.InitialDto;
import dto.PlayerDeckDto;
import dto.ResultDto;
import repository.CardRepository;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class BlackJackService {

    private final PlayerRepository playerRepository;
    private final CardRepository cardRepository;
    private final CardDistributor cardDistributor;
    private final JudgementService judgementService;

    public BlackJackService(
            PlayerRepository playerRepository,
            CardRepository cardRepository,
            CardDistributor cardDistributor,
            JudgementService judgementService
    ) {
        this.playerRepository = playerRepository;
        this.cardRepository = cardRepository;
        this.cardDistributor = cardDistributor;
        this.judgementService = judgementService;
    }

    // 플레이어 생성 후 카드 분배
    public InitialDto createPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .map(playerRepository::save)
                .toList();
        cardDistributor.initialize(players);

        CardDto dealerCard = CardDto.of(cardDistributor.getDealer().getDeck().getLastCard());
        List<PlayerDeckDto> playerDeckDtos = new ArrayList<>();
        for (Player player : players) {
            List<CardDto> cardDtos = player.getDeck().getCards().stream()
                    .map(CardDto::of)
                    .toList();
            playerDeckDtos.add(PlayerDeckDto.of(player.getName(), cardDtos));
        }
        return new InitialDto(dealerCard, playerDeckDtos);
    }

    public ResultDto judgement() {
        return judgementService.getGameResult();
    }
}
