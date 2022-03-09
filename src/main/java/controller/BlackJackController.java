package controller;

import service.dto.InitGameDto;
import service.dto.NamesDto;
import service.BlackJackService;

public class BlackJackController {

    private final BlackJackService service;
    public BlackJackController(BlackJackService service) {
        this.service = service;
    }

    public InitGameDto initGame(NamesDto namesDto) {
        return service.initGame(namesDto);
    }
}
