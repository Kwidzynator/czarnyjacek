package bazadanych;

import jakarta.persistence.*;

@Entity
public class Loginy {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "loginySeq")
    @SequenceGenerator(name = "loginySeq", sequenceName = "loginy_seq", allocationSize = 1)
    private int id;
    private String login;
    private String haslo;

    public Loginy() {
    }

    public Loginy(Integer id, String login, String haslo) {
        this.id = id;
        this.login = login;
        this.haslo = haslo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
