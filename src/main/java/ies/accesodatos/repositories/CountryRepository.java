package ies.accesodatos.repositories;

import ies.accesodatos.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("select distinct c.country from City c  where upper(c.city) like upper(?1) ")
    List<Country> findByCountryByCityLikeIgnoreCase(String city);
}