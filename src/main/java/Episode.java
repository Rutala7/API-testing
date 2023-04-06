import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Episode {

    Long id;
    String name;
    String air_date;
    String episode;
    List<String> characters;

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", air_date='" + air_date + '\'' +
                ", episode='" + episode + '\'' +
                ", characters=" + characters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode1 = (Episode) o;
        return Objects.equals(id, episode1.id) && Objects.equals(name, episode1.name) && Objects.equals(air_date, episode1.air_date) && Objects.equals(episode, episode1.episode) && Objects.equals(characters, episode1.characters);
    }
}
