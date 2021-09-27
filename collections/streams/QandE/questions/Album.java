import java.util.List;
import java.util.ArrayList;

public class Album {
  
    public String name;
    public List<Track> tracks = new ArrayList<Track>();

    public String toString() {
        return name + "" + tracks;
    }
    
}
