
package statistics.matcher;

import statistics.*;

public class QueryBuilder {
    private Matcher matcher;
    
    public QueryBuilder() {
        this.matcher = new All();
    }
    
    public Matcher build() {
        Matcher returnable = this.matcher;
        this.matcher = new All();
        return returnable;
        
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(new PlaysIn(team), matcher);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(new HasAtLeast(value, category), matcher);
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(new HasFewerThan(value, category), matcher);
        return this;
    }
    
    public QueryBuilder oneOf(Matcher matcher1, Matcher matcher2) {
        this.matcher = new Or(matcher1, matcher2);
        return this;
    }
}
