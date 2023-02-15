package hr.smatijasevic.urlservis.repository;

import hr.smatijasevic.urlservis.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    @Query("select u from Url u inner join Account a on a.id = u.account.id where a.accountId = ?1")
    Optional<List<Url>> findByAccountId(String accountId);
}
