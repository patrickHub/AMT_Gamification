package ch.heigvd.amt.gamification.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Eventtype
 */
@javax.annotation.Generated(value = "class ch.heigvd.amt.gamification.codegen.languages.SpringCodegen", date = "2016-12-18T13:30:19.867Z")

@Entity
@Table(name = "eventtype")
public class Eventtype   {
    @JsonProperty("id")
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("code")
    @Column(nullable = false)
    private BigDecimal code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventtype")
    private List<Event> events = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    public Eventtype name(String name) {
        this.name = name;
        return this;
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

    public Eventtype code(BigDecimal code) {
        this.code = code;
        return this;
    }

    /**
     * Get code
     * @return code
     **/
    @ApiModelProperty(value = "")
    public BigDecimal getCode() {
        return code;
    }

    public void setCode(BigDecimal code) {
        this.code = code;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Eventtype eventtype = (Eventtype) o;
        return Objects.equals(this.name, eventtype.name) &&
                Objects.equals(this.code, eventtype.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Eventtype {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
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
