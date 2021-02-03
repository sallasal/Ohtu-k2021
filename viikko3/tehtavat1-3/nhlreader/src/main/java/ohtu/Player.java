
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
        int total = goals + assists;
        return String.format("%20s %4s %2s %3s %2s %3s %3s", name, team, goals, " + ", assists, " = ", total);
    }
      
}
