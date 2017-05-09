package com.waterapp.repository.gateway;


import com.waterapp.domain.gateway.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends PagingAndSortingRepository<Gateway, String> {

    Page<Gateway> findByNameLike(Pageable page, String name);

    Page<Gateway> findByIdNotNull(Pageable page);

    @Query(value = "select * form assss where u.id = :id",nativeQuery = true)
    Gateway klsjdf(@Param("id")String id);


}
