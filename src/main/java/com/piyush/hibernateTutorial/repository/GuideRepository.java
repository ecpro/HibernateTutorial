package com.piyush.hibernateTutorial.repository;

import com.piyush.hibernateTutorial.model.touristGuide.Guide;
import com.piyush.hibernateTutorial.model.touristGuide.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class GuideRepository {

    @Autowired
    EntityManager em;

    public void findGuidesById(Long id) {
        Guide guide = em.find(Guide.class, id);
        System.out.println(guide);
        //List<Tourist> tourists = guide.getTourists();
        //System.out.println(tourists);
    }

}
