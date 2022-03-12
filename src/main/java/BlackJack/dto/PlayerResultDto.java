package BlackJack.dto;

public class PlayerResultDto {

    private final String name;
    private final String result;

    public PlayerResultDto(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
}

