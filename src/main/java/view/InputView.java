package view;

import static view.BlackJackGameCommand.NO;
import static view.BlackJackGameCommand.YES;
import static view.BlackJackGameCommand.contains;
import static view.BlackJackGameCommand.valueOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import view.dto.PlayerDto;
import view.dto.PlayersDto;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public PlayersDto askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = requireNotBlank(readLine());
        List<PlayerDto> players = Arrays.stream(input.split(","))
                .map(name -> new PlayerDto(name.trim()))
                .toList();
        return new PlayersDto(players);
    }

    private String requireNotBlank(final String input){
        if(input.isBlank()){
            throw new IllegalArgumentException("입력이 공백으로만 이루어질 수 없습니다.");
        }
        return input;
    }

    public BlackJackGameCommand askMoreCard(final PlayerDto playerDto) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerDto.name());
        return requireYesOrNo(readLine());
    }

    private BlackJackGameCommand requireYesOrNo(final String input){
        if (!contains(input)) {
            throw new IllegalArgumentException("%s 또는 %s을 입력하여 주세요".formatted(YES.getCommand(), NO.getCommand()));
        }
        return valueOf(input);
    }

    private String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
