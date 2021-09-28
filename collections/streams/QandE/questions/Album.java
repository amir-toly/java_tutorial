import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Album {
  
    public String name;
    public List<Track> tracks = new ArrayList<Track>();
  
    Album(String name, Track... tracks) {
        this.name = name;
        this.tracks = Arrays.asList(tracks);
    }

    public String toString() {
        return name + "" + tracks;
    }
    
}
