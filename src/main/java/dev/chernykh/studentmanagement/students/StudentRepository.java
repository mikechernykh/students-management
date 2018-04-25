package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.groups.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByGroup(Group group);
}
