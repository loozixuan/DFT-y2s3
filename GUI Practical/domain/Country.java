package domain;


import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable {
    private String name;
    private String fullName;
    private String capital;

    public Country() {
    }

    public Country(String name, String fullName, String capital) {
        this.name = name;
        this.fullName = fullName;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-50s %-50s %-50s", name, fullName, capital);
    }

    
    
    
    
}
