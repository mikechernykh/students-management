package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.groups.Group;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByGroup(Group group);
}
