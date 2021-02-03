
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return name + ", " + team + ". Goals: " + goals + ", assists " + assists + ".";
    }
      
}
