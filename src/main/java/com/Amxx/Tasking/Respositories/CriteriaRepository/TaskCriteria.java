package com.Amxx.Tasking.Respositories.CriteriaRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.Amxx.Tasking.Models.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskCriteria implements TaskCriteriaRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Task> findTaskByIdAndCantidad(Long id, Long cantidad) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        Root<Task> task = cq.from(Task.class);

        Predicate predicateId = cb.equal(task.get("id"), id);
        Predicate predicateCantidad = cb.equal(task.get("cantidad"), cantidad);
        cq.where(predicateId, predicateCantidad);

        return em.createQuery(cq).getResultList();
    }

}
