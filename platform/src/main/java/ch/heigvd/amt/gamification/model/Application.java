package ch.heigvd.amt.gamification.model;

import java.util.*;

import ch.heigvd.amt.gamification.configuration.AppConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * Application
 */
@javax.annotation.Generated(value = "class ch.heigvd.amt.gamification.codegen.languages.SpringCodegen", date = "2016-12-13T18:36:02.067Z")

@Entity
@Table(name = "application")
public class Application   {

    // An autogenerated id (unique for each application in the db)
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("name")
    @Column(length = AppConfig.MAX_APP_NAME_LENGTH, nullable = false, unique = true)
    private String name;

    @JsonProperty("password")
    @Column(length = AppConfig.MAX_APP_PWD_LENGTH, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    Set<Level> levels = new HashSet<>(); // levels are unique in a given application

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    Set<Badge> badges = new HashSet<>(); // badges are unique in a given application

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    List<Event> events = new LinkedList<>(); // events are not necessarly unique in a given application

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    Set<Eventtype> eventtypes = new HashSet<>();

    public Application() {
    }

    public Application(String name) {
        this.name = name;
    }

    public Application(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // TODO : nécessaire ?
    public Application name(String name) {
        this.name = name;
        return this;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    /**
     * Get name
     * @return name
     **/
    @ApiModelProperty(value = "")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Application password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get password
     * @return password
     **/
    @ApiModelProperty(value = "")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application application = (Application) o;
        return Objects.equals(this.name, application.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Application {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

