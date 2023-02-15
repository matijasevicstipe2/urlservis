package hr.smatijasevic.urlservis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.smatijasevic.urlservis.security.user.Account;
import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "redirect_type")
    private Integer redirectType;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "number_of_clicks")
    private Long numberOfClicks;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getNumberOfClicks() {
        return numberOfClicks;
    }

    public void setNumberOfClicks(Long numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
