import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Character {
    public Integer id;
    public String name;
    public String status;
    public String species;
    public String gender;
    public String type;
    public List<String> episodes;
    public Origin origin;
    public Location location;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", episodes=" + episodes +
                ", origin=" + origin +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) && Objects.equals(name, character.name) && Objects.equals(status, character.status) && Objects.equals(species, character.species) && Objects.equals(gender, character.gender) && Objects.equals(type, character.type) && Objects.equals(episodes, character.episodes) && Objects.equals(origin, character.origin) && Objects.equals(location, character.location);
    }

}
