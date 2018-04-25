package dev.chernykh.studentmanagement.groups;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
    @Query("select g.name from Group g")
    List<String> findNames();

    Group findByNameLike(String name);
}
