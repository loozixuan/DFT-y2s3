package domain;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private String id;
    private String ic;
    private String name;
    private char level;
    private Programme programme;
    private int year;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String ic, String name, char level, Programme programme, int year) {
        this.id = id;
        this.ic = ic;
        this.name = name;
        this.level = level;
        this.programme = programme;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-30s %-5c %-5s", id, ic, name, level, programme.getCode()+year);
    }
    
    
}
