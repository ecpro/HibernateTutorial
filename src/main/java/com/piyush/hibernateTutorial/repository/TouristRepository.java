package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.touristGuide.Guide;
import com.piyush.hibernateTutorial.model.touristGuide.Tourist;
import com.piyush.hibernateTutorial.model.touristGuide.TouristTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
@Transactional
public class TouristRepository {

    @Autowired
    EntityManager em;

    public Tourist findById(Long id) {
        return em.find(Tourist.class, id);
    }

    public void addTourist(Tourist tourist) {
        em.persist(tourist);
    }

    public void addGuideToTorist(Long touristId) {
        Tourist tourist = this.findById(touristId);
        List<Guide> guides = em.createNativeQuery("SELECT * FROM GUIDE", Guide.class).getResultList();
        tourist.setGuide(guides.get(1));
    }

    /**
     * Below code will throw : Error during managed flush [org.hibernate.TransientPropertyValueException:
     * object references an unsaved transient instance - save the transient instance before flushing :
     * com.piyush.hibernateTutorial.model.touristGuide.Tourist.guide ->
     * com.piyush.hibernateTutorial.model.touristGuide.Guide]
     *
     * Because Guide{Woolash} deesn't exist and we need to persist it before adding it to tourist object
     *
     */
    // ensure no CascadeType selected in Tourist Entity for below two methods
    public void addTouristAndGuideIncorrectImplementation() {
        Guide guide = new Guide("Woolash"); // need to persist before persisting tourist
        Tourist tourist = new Tourist("Tour7", guide, TouristTypeEnum.INTERNATIONAL);
        em.persist(tourist);
    }

    public void addTouristAndGuideCorrectImplementation() {
        Guide guide = new Guide("Woolash");
        em.persist(guide);
        Tourist tourist = new Tourist("Tour7", guide, TouristTypeEnum.INTERNATIONAL);
        em.persist(tourist);
    }

    /**
    In the above example we have to persist guide first before adding it to tourist object and
     eventually persisting tourist object.You would not like to call two separate persist method to first persist guide then tourist. Rather
     all the dependencies of Torist must be persisted while persisting tourist itself than persisting them separately.
     This can be done by cascading the persist operation also called TRANSITIVE PERSISTANCE.
     */

    // ensure cascadeType.PERSIST selected for below method to execute successfully
    public void addTouristAndGuideWithCascade() {
        Guide guide = new Guide("Woolash");
        Tourist tourist = new Tourist("Tour7", guide, TouristTypeEnum.INTERNATIONAL);
        em.persist(tourist);
    }

}
