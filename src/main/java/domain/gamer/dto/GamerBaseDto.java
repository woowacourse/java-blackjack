package domain.gamer.dto;

public abstract class GamerBaseDto {
    private String name;

    public GamerBaseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
