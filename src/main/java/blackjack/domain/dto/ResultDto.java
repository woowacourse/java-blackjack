package blackjack.domain.dto;

import blackjack.domain.Result;

import java.util.Objects;

public class ResultDto {
    private final String result;
    public ResultDto(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
