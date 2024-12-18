package fact.it.travelservice.repository;

import fact.it.travelservice.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByStartLocationAndEndLocation(String startLocation, String endLocation);

}
