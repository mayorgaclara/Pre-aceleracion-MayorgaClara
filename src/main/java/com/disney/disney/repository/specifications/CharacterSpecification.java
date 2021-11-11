package com.disney.disney.repository.specifications;

import com.disney.disney.dto.CharacterFiltersDTO;
import com.disney.disney.entity.CharacterEntity;
import com.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO characterFiltersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(characterFiltersDTO.getName())) {
                predicates.add(
                                criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if(characterFiltersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), characterFiltersDTO.getAge()								)
                );
            }

            if (!CollectionUtils.isEmpty(characterFiltersDTO.getMovies())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(characterFiltersDTO.getMovies()));
            }

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            String orderByField = "name";
            query.orderBy(
                    characterFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
